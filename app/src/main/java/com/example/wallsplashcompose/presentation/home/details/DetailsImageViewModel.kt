package com.example.wallsplashcompose.presentation.home.details

import android.app.WallpaperManager
import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import coil.request.ImageRequest
import com.example.wallsplashcompose.core.navigation.Routes
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.domain.repository.Downloader
import com.example.wallsplashcompose.domain.repository.ImageRepository
import com.example.wallsplashcompose.domain.repository.WallpaperSetter
import com.example.wallsplashcompose.presentation.components.ImageDownloadOptions
import com.example.wallsplashcompose.presentation.home.details.components.SetAsWallpaperOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsImageViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    private val downloader: Downloader,
    private val wallpaperManager: WallpaperSetter,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val imageId: String = checkNotNull(savedStateHandle["imageId"])
    var image: UnsplashImage? by mutableStateOf(null)
        private set

    init {
        getImage()
    }

    private fun getImage() {
        viewModelScope.launch {
            try {
                val result = imageRepository.getImage(imageId)
                image = result
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun downloadImage(url: String, title: String?) {
        viewModelScope.launch {
            try {
                downloader.downloadFile(url = url, fileName = title)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setWallpaper(image: Bitmap, type: SetAsWallpaperOptions) {
        viewModelScope.launch {
            try {
                wallpaperManager.setWallpaper(image, type)
            } catch (e: Exception) {
                if (e is CancellationException)
                    e.printStackTrace()
            }
        }
    }
}