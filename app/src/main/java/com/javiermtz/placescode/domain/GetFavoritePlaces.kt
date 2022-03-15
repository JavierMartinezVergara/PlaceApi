package com.javiermtz.placescode.domain

import androidx.lifecycle.LiveData
import com.javiermtz.placescode.data.repository.PlacesRepositoryImpl
import com.javiermtz.placescode.domain.models.Place
import javax.inject.Inject

class GetFavoritePlaces @Inject constructor(
  private val repository: PlacesRepositoryImpl
) {

  suspend operator fun invoke(): List<Place> {
    return repository.getFavoritePlaces()
  }

}


