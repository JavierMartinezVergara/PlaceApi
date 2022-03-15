package com.javiermtz.placescode.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.android.gms.common.api.Api
import com.javiermtz.placescode.data.api.ApiPlaces
import com.javiermtz.placescode.data.local.PlaceDatabase
import com.javiermtz.placescode.domain.models.Place
import com.javiermtz.placescode.model.responses.ResponsePlaces
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
  private val placesRepository: ApiPlaces,
  private val database: PlaceDatabase
) : PlacesRepository {

  override suspend fun getPlaces(location: String): Response<ResponsePlaces> {
    return placesRepository.getPlaces(location = location)
  }

  override suspend fun getFavoritePlaces(): List<Place> {

    return database.placeDao().getFavoritePlaces()

  }

  override suspend fun insertFavorite(place: Place) {
    database.placeDao().addFavoritePlace(place)
  }

}
