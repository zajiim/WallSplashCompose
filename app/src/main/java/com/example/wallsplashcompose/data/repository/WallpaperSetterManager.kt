package com.example.wallsplashcompose.data.repository

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import com.example.wallsplashcompose.domain.repository.WallpaperSetter
import com.example.wallsplashcompose.presentation.home.details.components.SetAsWallpaperOptions
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class WallpaperSetterManager @Inject constructor(
    @ApplicationContext context: Context
): WallpaperSetter {
    private val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(context)
    override suspend fun setWallpaper(image: Bitmap, type: SetAsWallpaperOptions) {
        withContext(Dispatchers.IO) {
            try {
                when(type) {
                    SetAsWallpaperOptions.LOCK_SCREEN -> wallpaperManager.setBitmap(image, null, true, WallpaperManager.FLAG_LOCK)
                    SetAsWallpaperOptions.HOME_SCREEN -> wallpaperManager.setBitmap(image, null, true, WallpaperManager.FLAG_SYSTEM)
                    SetAsWallpaperOptions.BOTH -> {
                        wallpaperManager.setBitmap(image, null, true, WallpaperManager.FLAG_SYSTEM)
                        wallpaperManager.setBitmap(image, null, true, WallpaperManager.FLAG_LOCK)
                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}