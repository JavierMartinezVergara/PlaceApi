package com.javiermtz.placescode.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.javiermtz.placescode.data.local.dao.PlaceDao
import com.javiermtz.placescode.domain.models.Place

@Database(entities = [Place::class], version = 1)
abstract class PlaceDatabase : RoomDatabase(){

  abstract fun placeDao() : PlaceDao
}
