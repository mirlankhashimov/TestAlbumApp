package com.example.testalbumapp.domain.datasource.album

import com.example.testalbumapp.domain.BaseDataSource
import com.example.testalbumapp.data.api.GalleryApi

class AlbumRemoteDataSource(private val api: GalleryApi) : BaseDataSource() {
    suspend fun fetchAlbums(page: Int, pageSize: Int) = getResult { api.getAlbums(page, pageSize) }
}