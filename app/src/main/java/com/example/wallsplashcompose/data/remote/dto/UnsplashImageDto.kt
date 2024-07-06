package com.example.wallsplashcompose.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashImageDto(
    @SerialName("id")
    val id: String,
    @SerialName("blur_hash")
    val blurHash: String?,
    @SerialName("color")
    val color: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("height")
    val height: Int,
    @SerialName("urls")
    val urls: Urls,
    @SerialName("user")
    val user: User,
    @SerialName("width")
    val width: Int
)

@Serializable
data class Urls(
    @SerialName("full")
    val full: String,
    @SerialName("raw")
    val raw: String,
    @SerialName("regular")
    val regular: String,
    @SerialName("small")
    val small: String,
    @SerialName("thumb")
    val thumb: String
)

@Serializable
data class User(
    @SerialName("links")
    val links: LinksX,
    @SerialName("name")
    val name: String,
    @SerialName("profile_image")
    val profileImage: ProfileImage,
    @SerialName("username")
    val username: String
)

@Serializable
data class ProfileImage(
    @SerialName("large")
    val large: String,
    @SerialName("medium")
    val medium: String,
    @SerialName("small")
    val small: String
)

@Serializable
data class LinksX(
    @SerialName("html")
    val html: String,
    @SerialName("likes")
    val likes: String,
    @SerialName("photos")
    val photos: String,
    @SerialName("portfolio")
    val portfolio: String,
    @SerialName("self")
    val self: String
)