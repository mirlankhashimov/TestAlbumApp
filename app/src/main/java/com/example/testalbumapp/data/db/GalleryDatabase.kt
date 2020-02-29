package com.example.testalbumapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testalbumapp.data.db.dao.AlbumDao
import com.example.testalbumapp.data.db.dao.PhotoDao
import com.example.testalbumapp.domain.entity.Album
import com.example.testalbumapp.domain.entity.Photo

@Database(
    version = 2,
    entities = [
        Album::class,
        Photo::class
    ]
)
abstract class GalleryDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
    abstract fun photoDao(): PhotoDao
}