package com.javiermtz.placescode.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.javiermtz.placescode.R
import com.javiermtz.placescode.databinding.FragmentFavoritesBinding
import com.javiermtz.placescode.databinding.FragmentSecondBinding
import com.javiermtz.placescode.presentation.adapter.PlaceAdapter
import com.javiermtz.placescode.presentation.adapter.PlaceAdapter.OnClickListener

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FavoritePlaceFragment : Fragment() {

  private val viewModel : PlacesViewModel by activityViewModels()

  private lateinit var binding: FragmentFavoritesBinding

  private lateinit var adapter: PlaceAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.getFavorites()
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentFavoritesBinding.inflate(inflater, container, false)
    return binding.root

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.favorites.observe(viewLifecycleOwner){
      adapter.submitList(it)
    }

    setViews()

  }

  override fun onDestroyView() {
    super.onDestroyView()
  }

  fun setViews(){
    adapter = PlaceAdapter(OnClickListener { placeItem ->
    })

    binding.recyclerFavoritesPlaces.adapter = adapter

  }
}
