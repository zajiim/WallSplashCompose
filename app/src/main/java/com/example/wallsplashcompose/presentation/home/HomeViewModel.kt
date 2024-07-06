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
import kotlinx.coroutines.launch

const val TAG = "HomeViewModel"
class HomeViewModel: ViewModel() {
    var images: List<UnsplashImage> by mutableStateOf(emptyList())
        private set

    init {
        getImages()
    }

    private fun getImages() {
        viewModelScope.launch {
            val result = AppModule.retrofitService.getEditorialFeedImages()
            Log.e(TAG, "getImages: $result")
            images = result.toUnsplashModelList()
        }
    }
}