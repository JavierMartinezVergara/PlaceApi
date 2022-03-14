package com.javiermtz.placescode

import android.Manifest
import android.Manifest.permission
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.PlaceLikelihood
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.javiermtz.placescode.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), OnMapReadyCallback {

  private var _binding: FragmentFirstBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  private lateinit var map : GoogleMap

  val options = GoogleMapOptions()
  lateinit var placesClient: PlacesClient
  lateinit var supportMap : SupportMapFragment

  var latLog: LatLng = LatLng(0.0,0.0)

  private lateinit var mFusedLocationClient: FusedLocationProviderClient

  // Use fields to define the data types to return.
  val placeFields: List<Place.Field> = listOf(Place.Field.NAME, Place.Field.LAT_LNG)

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentFirstBinding.inflate(inflater, container, false)

    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    return binding.root

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    supportMap = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    supportMap.getMapAsync(this)

    // Create a new PlacesClient instance
    placesClient = Places.createClient(requireContext())
    // Initialize the SDK

// Use the builder to create a FindCurrentPlaceRequest.

    val requestPermissionLauncher =
      registerForActivityResult(
        RequestPermission()
      ) { isGranted: Boolean ->
        if (isGranted) {
          // Permission is granted. Continue the action or workflow in your
          // app.
        } else {
          // Explain to the user that the feature is unavailable because the
          // features requires a permission that the user has denied. At the
          // same time, respect the user's decision. Don't link to system
          // settings in an effort to convince the user to change their
          // decision.
        }
      }

    onClickRequestPermission(requestPermissionLauncher)

//

  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  override fun onMapReady(googleMap: GoogleMap) {
    map = googleMap

    options.minZoomPreference(10F)
    map.addMarker(MarkerOptions()
      .position(latLog))
    map.uiSettings.isScrollGesturesEnabled = true

    map.uiSettings.isZoomControlsEnabled = true
    map.uiSettings.isMyLocationButtonEnabled = false


  }

  private fun isLocationEnabled(): Boolean {
    val locationManager: LocationManager =
      requireContext().getSystemService(LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
      LocationManager.NETWORK_PROVIDER
    )
  }

  fun onClickRequestPermission(requestPermissionLauncher: ActivityResultLauncher<String>) {
    val request: FindCurrentPlaceRequest = FindCurrentPlaceRequest.newInstance(placeFields)
    when {
      ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
      ) == PackageManager.PERMISSION_GRANTED &&
          ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
          ) == PackageManager.PERMISSION_GRANTED -> {
        mFusedLocationClient.lastLocation.addOnSuccessListener {
          map.isMyLocationEnabled = true
          map.addMarker(MarkerOptions().position(latLog))
          latLog = LatLng(it.latitude, it.longitude)
          map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLog, 18F))
        }
        mFusedLocationClient.lastLocation.addOnFailureListener {

        }

        val placeResponse = placesClient.findCurrentPlace(request)
        placeResponse.addOnCompleteListener { task ->
          if (task.isSuccessful) {
            val response = task.result
            for (placeLikelihood: PlaceLikelihood in response?.placeLikelihoods ?: emptyList()) {
              val t = response.placeLikelihoods[5].place.latLng
              map.moveCamera(CameraUpdateFactory.newLatLngZoom(t!!, 18F))
              map.addMarker(MarkerOptions().position(t))

              Log.e("TEST", "Place found: ${placeLikelihood.place}")
            }
          } else {
            val exception = task.exception
            if (exception is ApiException) {
              Log.e("TEST", "Place not found: ${exception.statusCode}")
            }
          }
        }
      }

      ActivityCompat.shouldShowRequestPermissionRationale(
        requireActivity(),
        permission.ACCESS_FINE_LOCATION
      ) -> {
        requestPermissionLauncher.launch(
          permission.ACCESS_FINE_LOCATION
        )
      }

      else -> {
        requestPermissionLauncher.launch(
          permission.ACCESS_FINE_LOCATION
        )
      }
    }
  }

}
