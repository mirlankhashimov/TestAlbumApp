package com.example.testalbumapp.data.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.example.testalbumapp.domain.entity.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(places: List<Album>)

    @Query("SELECT * FROM album")
    fun getAll(): DataSource.Factory<Int, Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(legoSets: List<Album>)
}