package com.example.testalbumapp.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album(
    @PrimaryKey
    var id: Int? = 0,
    val userId: Int? = 0,
    val title: String? = "",
    val indexInResponse: Int = -1
)