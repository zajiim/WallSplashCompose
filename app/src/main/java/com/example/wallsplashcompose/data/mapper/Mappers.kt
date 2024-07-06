package com.example.wallsplashcompose.data.mapper

import com.example.wallsplashcompose.data.remote.dto.UnsplashImageDto
import com.example.wallsplashcompose.domain.models.UnsplashImage

fun UnsplashImageDto.toUnsplashModel(): UnsplashImage {
    return UnsplashImage(
        id = this.id,
        description = description,
        imageUrlSmall = this.urls.small,
        imageUrlRegular = this.urls.regular,
        imageUrlRaw = this.urls.raw,
        blurHash = this.blurHash,
        photographerName = this.user.name,
        photographerUsername = this.user.username,
        photographerProfileImage = this.user.profileImage.small,
        photographerProfileLink = this.user.links.html,
        width = this.width,
        height = this.height
    )
}

fun List<UnsplashImageDto>.toUnsplashModelList(): List<UnsplashImage> {
    return this.map { it.toUnsplashModel() }
}