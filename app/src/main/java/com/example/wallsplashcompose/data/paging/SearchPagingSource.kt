package com.example.wallsplashcompose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wallsplashcompose.data.mapper.toUnsplashModelList
import com.example.wallsplashcompose.data.remote.UnsplashApiService
import com.example.wallsplashcompose.domain.models.UnsplashImage

class SearchPagingSource(
    private val query: String,
    private val unsplashApiService: UnsplashApiService
): PagingSource<Int, UnsplashImage>() {
    companion object {
        private const val INITIAL_PAGE_INDEX = 1
    }
    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
//        return state.anchorPosition
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        val currentPage = params.key ?: INITIAL_PAGE_INDEX
        return try {
            val response = unsplashApiService.searchImage(
                query = query,
                page = currentPage,
                perPage = params.loadSize
            )
            val endOfPaginationReached = response.images.isEmpty()
            LoadResult.Page (
                data = response.images.toUnsplashModelList(),
                prevKey = if (currentPage == INITIAL_PAGE_INDEX) null else currentPage - 1,
                nextKey = if (endOfPaginationReached) null else currentPage + 1
            )
        } catch (e:Exception) {
            LoadResult.Error(e)
        }
    }
}