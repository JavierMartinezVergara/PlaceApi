package com.javiermtz.placescode.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javiermtz.placescode.util.Constants.TABLE_NAME
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Entity(tableName = TABLE_NAME)
data class Place(
  @PrimaryKey(autoGenerate = false)
  val name : String,
  val lat: Double,
  val lng: Double,
  val placeId : String,
  val rating: Double,

  ) : Parcelable

@Serializable
data class Photo(
  val name : String,

  )


