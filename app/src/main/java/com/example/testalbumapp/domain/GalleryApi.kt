package com.example.testalbumapp.domain

import com.example.testalbumapp.domain.model.Album
import com.example.testalbumapp.domain.model.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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