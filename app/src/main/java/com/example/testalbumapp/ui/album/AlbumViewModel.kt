package com.example.testalbumapp.ui.album

import androidx.lifecycle.viewModelScope
import com.example.testalbumapp.core.BaseViewModel
import com.example.testalbumapp.repo.AlbumRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

class AlbumViewModel(
    private val albumRepository: AlbumRepository
) : BaseViewModel() {

    var connectivityAvailable: Boolean = true

    val albums by lazy {
        albumRepository.observeAlbums(
            connectivityAvailable, viewModelScope
        )
    }

}