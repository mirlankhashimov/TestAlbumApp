package com.example.testalbumapp.domain.datasource.photos

import com.example.testalbumapp.domain.BaseDataSource
import com.example.testalbumapp.data.api.GalleryApi

class PhotoRemoteDataSource(private val api: GalleryApi) : BaseDataSource() {
    suspend fun fetchData(id: Int) = getResult {  api.getPhotos(id) }
}