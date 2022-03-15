package com.javiermtz.placescode.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javiermtz.placescode.domain.GetFavoritePlaces
import com.javiermtz.placescode.domain.GetPlacesUseCase
import com.javiermtz.placescode.domain.InsertPlaceToFavorite
import com.javiermtz.placescode.domain.models.Place
import com.javiermtz.placescode.util.ResultWrapper.GenericError
import com.javiermtz.placescode.util.ResultWrapper.Loading
import com.javiermtz.placescode.util.ResultWrapper.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
  private val useCase : GetPlacesUseCase,
  private val getFavoritePlaces: GetFavoritePlaces,
  private val insertFavorite : InsertPlaceToFavorite

) : ViewModel(){


  private val _data = MutableLiveData<List<Place>>()
  val data : LiveData<List<Place>> = _data

  private val _favorites = MutableLiveData<List<Place>>()
  val favorites : LiveData<List<Place>> = _favorites

  private val _error = MutableLiveData<String>()
  val error : LiveData<String> = _error


  fun getPlaces(location : String){
    viewModelScope.launch {
      withContext(Dispatchers.IO){
        when(val data = useCase.invoke(location = location)){
          is GenericError -> _error.postValue(data.error)
          is Loading -> data.loading
          is Success -> _data.postValue(data.dataResponse)
        }
      }

    }
  }

  fun insertToFavorite(place : Place){
    viewModelScope.launch {
      withContext(Dispatchers.IO){
        insertFavorite.invoke(place)
      }
    }
  }

  fun getFavorites(){
    viewModelScope.launch {
      withContext(Dispatchers.IO){
        _favorites.postValue(getFavoritePlaces.invoke())
      }
    }
  }


}
