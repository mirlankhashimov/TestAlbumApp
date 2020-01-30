package com.example.testalbumapp.ui.photos

import com.example.testalbumapp.core.BaseViewModel
import com.example.testalbumapp.repo.PhotoRepository

class PhotosViewModel(private val repository: PhotoRepository): BaseViewModel() {
    fun photos(id: Int) = repository.photos(id)
}