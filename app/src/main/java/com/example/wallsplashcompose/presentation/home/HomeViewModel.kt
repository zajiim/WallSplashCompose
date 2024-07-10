package com.example.wallsplashcompose.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallsplashcompose.core.di.AppModule
import com.example.wallsplashcompose.data.mapper.toUnsplashModelList
import com.example.wallsplashcompose.data.remote.dto.UnsplashImageDto
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "HomeViewModel"
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val imageRepository: ImageRepository
): ViewModel() {
    var images: List<UnsplashImage> by mutableStateOf(emptyList())
        private set

    init {
        getImages()
    }

    private fun getImages() {
        viewModelScope.launch {
//            val result = AppModule.retrofitService.getEditorialFeedImages()
            val result = imageRepository.getHomeImages()
            Log.e(TAG, "getImages: $result")
            images = result
        }
    }

    fun onImageClick(imageId: String) {
        Log.d(TAG, "Image clicked: $imageId")
    }

    fun onSearchClick() {
        Log.d(TAG, "Search clicked")
    }
}