package com.example.wallsplashcompose.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallsplashcompose.utils.Constants

@Entity(tableName = Constants.UNSPLASH_REMOTE_KEYS_TABLE)
data class UnsplashRemoteKeys(
    @PrimaryKey
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
