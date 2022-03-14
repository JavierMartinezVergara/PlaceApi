package com.javiermtz.placescode.util


sealed class ResultWrapper<out T> {
  data class Success<out T>(val dataResponse: T): ResultWrapper<T>()
  data class GenericError(val error: String? = null): ResultWrapper<Nothing>()
  data class Loading(val loading : Boolean = true): ResultWrapper<Nothing>()
}
