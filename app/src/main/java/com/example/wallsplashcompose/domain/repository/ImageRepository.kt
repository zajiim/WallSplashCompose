package com.example.wallsplashcompose.domain.repository

import androidx.paging.PagingData
import com.example.wallsplashcompose.domain.models.UnsplashImage
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun getHomeImages(): List<UnsplashImage>
    suspend fun getImage(imageId: String): UnsplashImage
    suspend fun searchImage(query: String): Flow<PagingData<UnsplashImage>>
}