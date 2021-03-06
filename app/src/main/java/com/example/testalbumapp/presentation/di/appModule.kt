package com.example.testalbumapp.presentation.di

import androidx.room.Room
import com.example.testalbumapp.BuildConfig
import com.example.testalbumapp.data.db.GalleryDatabase
import com.example.testalbumapp.data.api.GalleryApi
import com.example.testalbumapp.domain.datasource.album.AlbumRemoteDataSource
import com.example.testalbumapp.domain.datasource.photos.PhotoRemoteDataSource
import com.example.testalbumapp.domain.repo.AlbumRepository
import com.example.testalbumapp.domain.repo.PhotoRepository
import com.example.testalbumapp.presentation.album.AlbumViewModel
import com.example.testalbumapp.presentation.photos.PhotosViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {
    single { GsonBuilder().create() }
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
    single {
        Room.databaseBuilder(get(), GalleryDatabase::class.java, "gallery_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<Retrofit>().create<GalleryApi>() }
    single { get<GalleryDatabase>().photoDao() }
    single { get<GalleryDatabase>().albumDao() }
    single { AlbumRepository(get(), get()) }
    single { PhotoRepository(get(), get()) }
    single { AlbumRemoteDataSource(get()) }
    single { PhotoRemoteDataSource(get()) }
    viewModel { PhotosViewModel(get()) }
    viewModel { AlbumViewModel(get()) }

}

