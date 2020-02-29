package com.example.testalbumapp.domain.datasource.album

import androidx.paging.PageKeyedDataSource
import com.example.testalbumapp.data.db.GalleryDatabase
import com.example.testalbumapp.domain.entity.Album
import com.example.testalbumapp.utils.Result
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class AlbumPageDataSource(
    private val dataSource: AlbumRemoteDataSource,
    private val database: GalleryDatabase,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, Album>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Album>
    ) {
        fetchData(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, pageSize: Int, callback: (List<Album>) -> Unit) {
        scope.launch {
            getJobErrorHandler()
            val response = dataSource.fetchAlbums(page, pageSize)
            if (response.status == Result.Status.SUCCESS) {
                val results = response.data!!
                database.albumDao().insertAll(results)
                callback(results)
            } else if (response.status == Result.Status.ERROR) {
                postError(response.message!!)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Timber.e("An error happened: $message")
        // TODO network error handling
        //networkState.postValue(NetworkState.FAILED)
    }
}