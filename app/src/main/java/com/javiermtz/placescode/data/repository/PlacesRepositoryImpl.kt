package com.javiermtz.placescode.data.repository

import com.google.android.gms.common.api.Api
import com.javiermtz.placescode.data.api.ApiPlaces
import com.javiermtz.placescode.model.responses.ResponsePlaces
import retrofit2.Response
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
  private val placesRepository: ApiPlaces
) : PlacesRepository{


  override suspend fun getPlaces(location: String): Response<ResponsePlaces> {
    return placesRepository.getPlaces(location = location)
  }
}
