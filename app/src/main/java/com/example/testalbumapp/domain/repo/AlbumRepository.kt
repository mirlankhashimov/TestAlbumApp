package com.example.testalbumapp.domain.repo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.testalbumapp.data.db.GalleryDatabase
import com.example.testalbumapp.domain.datasource.album.AlbumPageDataSourceFactory
import com.example.testalbumapp.domain.datasource.album.AlbumRemoteDataSource
import com.example.testalbumapp.domain.entity.Album
import kotlinx.coroutines.CoroutineScope

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