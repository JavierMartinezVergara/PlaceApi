package com.javiermtz.placescode.data.api

import com.javiermtz.placescode.BuildConfig
import com.javiermtz.placescode.model.responses.ResponsePlaces
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiPlaces {

@GET("/maps/api/place/nearbysearch/json")
suspend fun getPlaces(
  @Query("location") location : String,
  @Query("radius") radius : Int = 500,
  @Query("key") key : String = BuildConfig.API_KEY,
  ) : Response<ResponsePlaces>
}
