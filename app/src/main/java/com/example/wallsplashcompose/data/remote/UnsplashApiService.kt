package com.example.wallsplashcompose.data.remote

import com.example.wallsplashcompose.data.remote.dto.UnsplashImageDto
import com.example.wallsplashcompose.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers

interface UnsplashApiService {
    @Headers("Authorization: Client-ID ${Constants.API_KEY}")
    @GET("/photos")
    suspend fun getEditorialFeedImages(): List<UnsplashImageDto>
}