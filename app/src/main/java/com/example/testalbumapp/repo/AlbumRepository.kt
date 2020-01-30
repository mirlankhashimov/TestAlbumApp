package com.example.testalbumapp.repo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.testalbumapp.db.GalleryDatabase
import com.example.testalbumapp.domain.datasource.album.AlbumPageDataSourceFactory
import com.example.testalbumapp.domain.datasource.album.AlbumRemoteDataSource
import com.example.testalbumapp.domain.model.Album
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.koin.core.KoinComponent
import org.koin.core.inject

class AlbumRepository(
    private val database: GalleryDatabase,
    private val remoteDataSource: AlbumRemoteDataSource
) {

    fun observeAlbums(connectivityAvailable: Boolean, coroutineScope: CoroutineScope) =
        if (connectivityAvailable) observeRemoteAlbums(coroutineScope)
        else observeLocalAlbums()

    private fun observeLocalAlbums(): LiveData<PagedList<Album>> {
        val dataSourceFactory = database.albumDao().getAll()
        return LivePagedListBuilder(
            dataSourceFactory,
            AlbumPageDataSourceFactory.pagedListConfig()
        ).build()
    }

    private fun observeRemoteAlbums(coroutineScope: CoroutineScope): LiveData<PagedList<Album>> {
        val dataSourceFactory = AlbumPageDataSourceFactory(remoteDataSource, database, coroutineScope)
        return LivePagedListBuilder(
            dataSourceFactory,
            AlbumPageDataSourceFactory.pagedListConfig()
        ).build()
    }
}