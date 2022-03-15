package com.javiermtz.placescode.di

import android.content.Context
import androidx.room.Room
import com.javiermtz.placescode.data.local.PlaceDatabase
import com.javiermtz.placescode.util.Constants.DATABASE_PLACE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context): PlaceDatabase {
    return Room.databaseBuilder(
      context,
      PlaceDatabase::class.java,
      DATABASE_PLACE_NAME)
      .fallbackToDestructiveMigration()
      .build()
  }

}
