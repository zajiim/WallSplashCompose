package com.example.wallsplashcompose.domain.models

data class UnsplashImage(
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
