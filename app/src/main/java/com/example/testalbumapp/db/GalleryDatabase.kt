package com.example.testalbumapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testalbumapp.db.dao.AlbumDao
import com.example.testalbumapp.db.dao.PhotoDao
import com.example.testalbumapp.domain.model.Album
import com.example.testalbumapp.domain.model.Photo

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