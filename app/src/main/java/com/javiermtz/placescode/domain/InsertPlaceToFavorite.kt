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

class InsertPlaceToFavorite @Inject constructor(
  private val repository: PlacesRepositoryImpl
) {

  suspend operator fun invoke(place: Place) {

    repository.insertFavorite(place)

  }

}


