package com.example.wallsplashcompose.data.mapper

import com.example.wallsplashcompose.data.local.entity.FavoriteImageEntity
import com.example.wallsplashcompose.data.local.entity.UnsplashImageEntity
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

fun UnsplashImage.toFavImageEntity(): FavoriteImageEntity {
    return FavoriteImageEntity(
        id = this.id,
        description = description,
        imageUrlSmall = this.imageUrlSmall,
        imageUrlRegular = this.imageUrlRegular,
        imageUrlRaw = this.imageUrlRaw,
        blurHash = this.blurHash,
        photographerName = this.photographerName,
        photographerUsername = this.photographerUsername,
        photographerProfileImage = this.photographerProfileImage,
        photographerProfileLink = this.photographerProfileLink,
        width = this.width,
        height = this.height
    )
}

fun FavoriteImageEntity.toUnSplashModel(): UnsplashImage {
    return UnsplashImage(
        id = this.id,
        description = description,
        imageUrlSmall = this.imageUrlSmall,
        imageUrlRegular = this.imageUrlRegular,
        imageUrlRaw = this.imageUrlRaw,
        blurHash = this.blurHash,
        photographerName = this.photographerName,
        photographerUsername = this.photographerUsername,
        photographerProfileImage = this.photographerProfileImage,
        photographerProfileLink = this.photographerProfileLink,
        width = this.width,
        height = this.height
    )
}

fun UnsplashImageDto.toUnsplashEntity(): UnsplashImageEntity {
    return UnsplashImageEntity(
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

fun List<UnsplashImageDto>.toUnsplashEntityList(): List<UnsplashImageEntity> {
    return this.map { it.toUnsplashEntity() }
}

fun UnsplashImageEntity.toUnSplashModel(): UnsplashImage {
    return UnsplashImage(
        id = this.id,
        description = description,
        imageUrlSmall = this.imageUrlSmall,
        imageUrlRegular = this.imageUrlRegular,
        imageUrlRaw = this.imageUrlRaw,
        blurHash = this.blurHash,
        photographerName = this.photographerName,
        photographerUsername = this.photographerUsername,
        photographerProfileImage = this.photographerProfileImage,
        photographerProfileLink = this.photographerProfileLink,
        width = this.width,
        height = this.height
    )
}