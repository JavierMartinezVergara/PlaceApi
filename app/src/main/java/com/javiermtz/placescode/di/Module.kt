package com.javiermtz.placescode.di

import com.javiermtz.placescode.data.repository.PlacesRepository
import com.javiermtz.placescode.data.repository.PlacesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class Module {

  @Binds
  abstract fun bindRepository(
    placesRepositoryImpl: PlacesRepositoryImpl
  ): PlacesRepository
}
