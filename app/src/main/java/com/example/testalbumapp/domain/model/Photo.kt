package com.example.testalbumapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    @PrimaryKey
    val id: Int = 0,
    val albumId: Int = 0,
    val title: String? = "",
    val url: String? = "",
    val thumbnailUrl: String? = "",
    val indexInResponse: Int = -1
)