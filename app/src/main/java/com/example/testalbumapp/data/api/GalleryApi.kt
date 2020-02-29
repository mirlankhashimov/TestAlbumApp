package com.example.testalbumapp.data.api

import com.example.testalbumapp.domain.entity.Album
import com.example.testalbumapp.domain.entity.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryApi {
    @GET("albums")
    suspend fun getAlbums(
        @Query("_start") pageIndex: Int,
        @Query("_limit") pageSize: Int = 20
    ): Response<List<Album>>

    @GET("photos")
    suspend fun getPhotos(@Query("albumId") albumId: Int): Response<List<Photo>>

}