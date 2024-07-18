package com.example.wallsplashcompose.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashImageSearchResult(
    @SerialName("results")
    val images: List<UnsplashImageDto>,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int
)