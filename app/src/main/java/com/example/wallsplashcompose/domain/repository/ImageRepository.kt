package com.example.wallsplashcompose.domain.repository

import com.example.wallsplashcompose.domain.models.UnsplashImage

interface ImageRepository {
    suspend fun getHomeImages(): List<UnsplashImage>
}