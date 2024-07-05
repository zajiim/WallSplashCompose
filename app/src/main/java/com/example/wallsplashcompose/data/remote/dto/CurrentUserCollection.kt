package com.example.wallsplashcompose.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentUserCollection(
    @SerialName("cover_photo")
    val coverPhoto: String,
    @SerialName("id")
    val id: Int,
    @SerialName("last_collected_at")
    val lastCollectedAt: String,
    @SerialName("published_at")
    val publishedAt: String,
    @SerialName("title")
    val title: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("user")
    val user: String
)