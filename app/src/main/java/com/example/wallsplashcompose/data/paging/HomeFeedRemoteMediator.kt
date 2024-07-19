package com.example.wallsplashcompose.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.wallsplashcompose.data.local.WallSplashDatabase
import com.example.wallsplashcompose.data.local.entity.UnsplashImageEntity
import com.example.wallsplashcompose.data.local.entity.UnsplashRemoteKeys
import com.example.wallsplashcompose.data.mapper.toUnsplashEntityList
import com.example.wallsplashcompose.data.remote.UnsplashApiService
import com.example.wallsplashcompose.utils.Constants

@OptIn(ExperimentalPagingApi::class)
class HomeFeedRemoteMediator(
    private val apiService: UnsplashApiService,
    private val database: WallSplashDatabase
): RemoteMediator<Int, UnsplashImageEntity>() {

    companion object{
        private const val STARTING_PAGE_INDEX = 1
    }

    private val homeFeedDao = database.homeFeedDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashImageEntity>,
    ): MediatorResult {
        try {
            val currentPage = when(loadType) {
                LoadType.REFRESH -> {
                    STARTING_PAGE_INDEX
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    nextPage
                }
            }
            val response = apiService.getAllImages(
                page = currentPage,
                perPage = Constants.PER_PAGE_ITEMS
            )
            val endOfPaginationReached = response.isEmpty()
            val prevPage = if(currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    homeFeedDao.deleteHomeImages()
                    homeFeedDao.deleteAllRemoteKeys()
                }
                val remoteKeys = response.map { unsplashImageDto ->
                    UnsplashRemoteKeys(
                        id = unsplashImageDto.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                homeFeedDao.insertAllRemoteKeys(remoteKeys)
                homeFeedDao.insertHomeImages(response.toUnsplashEntityList())
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, UnsplashImageEntity>
    ): UnsplashRemoteKeys? {
            return state.pages.firstOrNull{ it.data.isNotEmpty() }?.data?.firstOrNull()
                ?.let { unsplashImage ->
                    homeFeedDao.getRemoteKeys(id = unsplashImage.id)
                }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, UnsplashImageEntity>
    ): UnsplashRemoteKeys? {
        return state.pages.lastOrNull(){ it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                homeFeedDao.getRemoteKeys(id = unsplashImage.id)
            }
    }
}