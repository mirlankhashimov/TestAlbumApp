package com.example.testalbumapp.repo

import com.example.testalbumapp.db.GalleryDatabase
import com.example.testalbumapp.domain.datasource.photos.PhotoRemoteDataSource
import com.example.testalbumapp.domain.datasource.resultLiveData

class PhotoRepository(
    private val db: GalleryDatabase,
    private val photoRemoteDataSource: PhotoRemoteDataSource
) {
    fun photos(id:Int) = resultLiveData(
        databaseQuery = {db.photoDao().getPhotos()},
        networkCall = {photoRemoteDataSource.fetchData(id)},
        saveCallResult = {db.photoDao().insertAll(it)}
    )
}