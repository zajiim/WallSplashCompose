package com.example.wallsplashcompose.domain.repository

import androidx.paging.PagingData
import com.example.wallsplashcompose.domain.models.UnsplashImage
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun getHomeImages(): List<UnsplashImage>
    suspend fun getImage(imageId: String): UnsplashImage
    fun searchImage(query: String): Flow<PagingData<UnsplashImage>>
    fun getAllFavImages(): Flow<PagingData<UnsplashImage>>
    suspend fun toggleFavStatus(image: UnsplashImage)
    fun getFavImageIds(): Flow<List<String>>
}