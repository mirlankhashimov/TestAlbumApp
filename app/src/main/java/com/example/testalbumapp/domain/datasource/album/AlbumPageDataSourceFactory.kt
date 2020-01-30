package com.example.testalbumapp.domain.datasource.album

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.testalbumapp.db.GalleryDatabase
import com.example.testalbumapp.domain.model.Album
import kotlinx.coroutines.CoroutineScope

class AlbumPageDataSourceFactory(
    private val dataSource: AlbumRemoteDataSource,
    private val database: GalleryDatabase,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Album>() {
    ///private val scope: CoroutineScope by inject()
    private val livedata = MutableLiveData<AlbumPageDataSource>()
    override fun create(): DataSource<Int, Album> {
        val source = AlbumPageDataSource(dataSource, database, scope)
        livedata.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 20
        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }

}
