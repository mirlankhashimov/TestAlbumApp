package com.example.testalbumapp.domain.repo

import com.example.testalbumapp.data.db.GalleryDatabase
import com.example.testalbumapp.domain.datasource.photos.PhotoRemoteDataSource

class PhotoRepository(
    private val db: GalleryDatabase,
    private val photoRemoteDataSource: PhotoRemoteDataSource
) {

}