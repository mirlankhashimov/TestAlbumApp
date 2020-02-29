package com.example.testalbumapp.presentation.photos

import com.example.testalbumapp.core.BaseViewModel
import com.example.testalbumapp.domain.repo.PhotoRepository

class PhotosViewModel(private val repository: PhotoRepository): BaseViewModel() {
    fun photos(id: Int) = repository.photos(id)
}