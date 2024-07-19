package com.example.wallsplashcompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.wallsplashcompose.data.local.WallSplashDatabase
import com.example.wallsplashcompose.data.mapper.toFavImageEntity
import com.example.wallsplashcompose.data.mapper.toUnSplashModel
import com.example.wallsplashcompose.data.mapper.toUnsplashModel
import com.example.wallsplashcompose.data.mapper.toUnsplashModelList
import com.example.wallsplashcompose.data.paging.HomeFeedRemoteMediator
import com.example.wallsplashcompose.data.paging.SearchPagingSource
import com.example.wallsplashcompose.data.remote.UnsplashApiService
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.domain.repository.ImageRepository
import com.example.wallsplashcompose.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ImageRepositoryImpl(
    private val unsplashApi: UnsplashApiService,
    private val database: WallSplashDatabase
): ImageRepository {
    private val favImagesDao = database.favImagesDao()
    private val homeFeedDao = database.homeFeedDao()
    @OptIn(ExperimentalPagingApi::class)
    override fun getHomeImages(): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PER_PAGE_ITEMS
            ),
            remoteMediator = HomeFeedRemoteMediator(
                apiService = unsplashApi,
                database = database
            ),
            pagingSourceFactory = {
                homeFeedDao.getAllHomeImages()
            }
        ).flow
            .map { pagingData ->
                pagingData.map { it.toUnSplashModel() }
            }
    }

    override suspend fun getImage(imageId: String): UnsplashImage {
        return unsplashApi.getImage(imageId).toUnsplashModel()
    }

    override fun searchImage(query: String): Flow<PagingData<UnsplashImage>> {
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

    override fun getAllFavImages(): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PER_PAGE_ITEMS
            ),
            pagingSourceFactory = {
                favImagesDao.getAllFavImages()
            }
        ).flow
            .map { pagingData ->
                pagingData.map { it.toUnSplashModel() }
            }
    }

    override suspend fun toggleFavStatus(image: UnsplashImage) {
        val isFavorite = favImagesDao.isImageFav(image.id)
        val favImage = image.toFavImageEntity()
        if (isFavorite) {
            favImagesDao.deleteFavImage(favImage)
        } else {
            favImagesDao.insertFavImage(favImage)
        }
    }

    override fun getFavImageIds(): Flow<List<String>> {
        return favImagesDao.getFavImageByIds()
    }
}