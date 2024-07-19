package com.example.wallsplashcompose.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallsplashcompose.utils.Constants

@Entity(
    tableName = Constants.UNSPLASH_IMAGE_TABLE
)
data class UnsplashImageEntity(
    @PrimaryKey
    val id: String,
    val imageUrlSmall: String,
    val imageUrlRegular: String,
    val imageUrlRaw: String,
    val blurHash: String?,
    val description: String?,
    val photographerName: String,
    val photographerUsername: String,
    val photographerProfileImage: String,
    val photographerProfileLink: String,
    val width: Int,
    val height: Int
)
