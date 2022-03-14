package com.javiermtz.placescode.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javiermtz.placescode.domain.GetPlacesUseCase
import com.javiermtz.placescode.domain.models.Place
import com.javiermtz.placescode.util.ResultWrapper.GenericError
import com.javiermtz.placescode.util.ResultWrapper.Loading
import com.javiermtz.placescode.util.ResultWrapper.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
  private val useCase : GetPlacesUseCase
) : ViewModel(){


  private val _data = MutableLiveData<List<Place>>()
  val data : LiveData<List<Place>> = _data

  private val _error = MutableLiveData<String>()
  val error : LiveData<String> = _error





  fun getPlaces(location : String){
    viewModelScope.launch {
      when(val data = useCase.invoke(location = location)){
        is GenericError -> _error.postValue(data.error)
        is Loading -> data.loading
        is Success -> _data.postValue(data.dataResponse)
      }
    }
  }


}
