package com.javiermtz.placescode.data.repository

import androidx.lifecycle.LiveData
import com.javiermtz.placescode.domain.models.Place
import com.javiermtz.placescode.model.responses.ResponsePlaces
import retrofit2.Response

interface PlacesRepository {

  suspend fun getPlaces(location : String) : Response<ResponsePlaces>

  suspend fun getFavoritePlaces() : List<Place>

  suspend fun insertFavorite(place : Place)
}
