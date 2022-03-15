package com.javiermtz.placescode.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javiermtz.placescode.domain.models.Place
import com.javiermtz.placescode.util.Constants.TABLE_NAME

@Dao
interface PlaceDao {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getFavoritePlaces(): List<Place>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoritePlace(place : Place)

}
