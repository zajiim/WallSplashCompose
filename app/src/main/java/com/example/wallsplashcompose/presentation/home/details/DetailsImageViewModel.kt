package com.example.wallsplashcompose.presentation.home.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.wallsplashcompose.core.navigation.Routes
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.domain.repository.Downloader
import com.example.wallsplashcompose.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsImageViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    private val downloader: Downloader,
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
}