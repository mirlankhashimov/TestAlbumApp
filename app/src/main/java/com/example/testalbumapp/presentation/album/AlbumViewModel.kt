package com.example.testalbumapp.presentation.album

import androidx.lifecycle.viewModelScope
import com.example.testalbumapp.core.BaseViewModel
import com.example.testalbumapp.domain.repo.AlbumRepository

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