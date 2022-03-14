package com.javiermtz.placescode.data.repository

import com.javiermtz.placescode.model.responses.ResponsePlaces
import retrofit2.Response

interface PlacesRepository {

  suspend fun getPlaces(location : String) : Response<ResponsePlaces>
}
