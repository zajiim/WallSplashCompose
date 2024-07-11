package com.example.wallsplashcompose.data.repository

import com.example.wallsplashcompose.data.mapper.toUnsplashModel
import com.example.wallsplashcompose.data.mapper.toUnsplashModelList
import com.example.wallsplashcompose.data.remote.UnsplashApiService
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.domain.repository.ImageRepository

class ImageRepositoryImpl(
    private val unsplashApi: UnsplashApiService
): ImageRepository {
    override suspend fun getHomeImages(): List<UnsplashImage> {
       return unsplashApi.getAllImages().toUnsplashModelList()
    }

    override suspend fun getImage(imageId: String): UnsplashImage {
        return unsplashApi.getImage(imageId).toUnsplashModel()
    }
}