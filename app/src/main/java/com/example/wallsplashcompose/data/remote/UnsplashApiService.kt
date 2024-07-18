package com.example.wallsplashcompose.data.remote

import com.example.wallsplashcompose.data.remote.dto.UnsplashImageDto
import com.example.wallsplashcompose.data.remote.dto.UnsplashImageSearchResult
import com.example.wallsplashcompose.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApiService {
    @Headers("Authorization: Client-ID ${Constants.API_KEY}")
    @GET("/photos")
    suspend fun getAllImages(): List<UnsplashImageDto>


    @Headers("Authorization: Client-ID ${Constants.API_KEY}")
    @GET("/photos/{id}")
    suspend fun getImage(
        @Path("id") imageId: String
    ): UnsplashImageDto


    @Headers("Authorization: Client-ID ${Constants.API_KEY}")
    @GET("/search/photos")
    suspend fun searchImage(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): UnsplashImageSearchResult

}