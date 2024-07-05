package com.example.wallsplashcompose.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("bio")
    val bio: String,
    @SerialName("id")
    val id: String,
    @SerialName("instagram_username")
    val instagramUsername: String,
    @SerialName("links")
    val links: LinksX,
    @SerialName("location")
    val location: String,
    @SerialName("name")
    val name: String,
    @SerialName("portfolio_url")
    val portfolioUrl: String,
    @SerialName("profile_image")
    val profileImage: ProfileImage,
    @SerialName("total_collections")
    val totalCollections: Int,
    @SerialName("total_likes")
    val totalLikes: Int,
    @SerialName("total_photos")
    val totalPhotos: Int,
    @SerialName("twitter_username")
    val twitterUsername: String,
    @SerialName("username")
    val username: String
)