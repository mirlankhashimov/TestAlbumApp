package com.example.testalbumapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testalbumapp.domain.model.Photo

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo ORDER BY id DESC")
    fun getPhotos(): LiveData<List<Photo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<Photo>)
}