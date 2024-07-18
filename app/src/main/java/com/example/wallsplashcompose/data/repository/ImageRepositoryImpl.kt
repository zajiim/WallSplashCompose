package com.example.wallsplashcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wallsplashcompose.data.mapper.toUnsplashModel
import com.example.wallsplashcompose.data.mapper.toUnsplashModelList
import com.example.wallsplashcompose.data.paging.SearchPagingSource
import com.example.wallsplashcompose.data.remote.UnsplashApiService
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.domain.repository.ImageRepository
import com.example.wallsplashcompose.utils.Constants
import kotlinx.coroutines.flow.Flow

class ImageRepositoryImpl(
    private val unsplashApi: UnsplashApiService
): ImageRepository {
    override suspend fun getHomeImages(): List<UnsplashImage> {
       return unsplashApi.getAllImages().toUnsplashModelList()
    }

    override suspend fun getImage(imageId: String): UnsplashImage {
        return unsplashApi.getImage(imageId).toUnsplashModel()
    }

    override suspend fun searchImage(query: String): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PER_PAGE_ITEMS
            ),
            pagingSourceFactory = {
                SearchPagingSource(
                    query = query,
                    unsplashApiService = unsplashApi
                )
            }
        ).flow
    }
}