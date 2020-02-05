package com.example.testalbumapp.repo

import com.example.testalbumapp.db.GalleryDatabase
import com.example.testalbumapp.domain.datasource.photos.PhotoRemoteDataSource

class PhotoRepository(
    private val db: GalleryDatabase,
    private val photoRemoteDataSource: PhotoRemoteDataSource
) {

}