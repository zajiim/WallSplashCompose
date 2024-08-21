package com.example.wallsplashcompose.domain.repository

import android.graphics.Bitmap
import com.example.wallsplashcompose.presentation.home.details.components.SetAsWallpaperOptions

interface WallpaperSetter {
    suspend fun setWallpaper(image: Bitmap, type: SetAsWallpaperOptions)
}