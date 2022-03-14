package com.javiermtz.placescode.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Place(
  val name : String,
  val lat: Double,
  val lng: Double,
  val placeId : String,
  val rating: Double,

  )

@Serializable
data class Photo(
  val name : String,

  )


