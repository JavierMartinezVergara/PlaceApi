package com.javiermtz.placescode.domain

import android.provider.Settings.Global
import androidx.core.graphics.alpha
import com.javiermtz.placescode.data.repository.PlacesRepository
import com.javiermtz.placescode.data.repository.PlacesRepositoryImpl
import com.javiermtz.placescode.domain.models.Place
import com.javiermtz.placescode.model.responses.ResponsePlaces
import com.javiermtz.placescode.util.ResultWrapper
import com.javiermtz.placescode.util.ResultWrapper.GenericError
import com.javiermtz.placescode.util.ResultWrapper.Loading
import com.javiermtz.placescode.util.ResultWrapper.Success
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(
  private val repository: PlacesRepositoryImpl
) {

  suspend operator fun invoke(location: String): ResultWrapper<List<Place>> {

    return try {
      Loading()
      val response = repository.getPlaces(location = location)
      return if (response.isSuccessful) {

        Success(response.body()?.toPlace() ?: listOf())
      } else
        GenericError(response.message())
    } catch (e: Exception) {
      GenericError(e.message)
    }

  }

}


fun ResponsePlaces.toPlace() : MutableList<Place> {
  val list : MutableList<Place> = mutableListOf()
  this.results.forEach {
    list.add(
      Place(
        name = it.name,
        lat = it.geometry.location.lat,
        lng = it.geometry.location.lng,
        rating = it.rating,
        placeId = it.place_id
      )
    )
  }

  return list

}
