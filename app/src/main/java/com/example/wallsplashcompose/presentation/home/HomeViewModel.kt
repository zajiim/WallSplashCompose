package com.example.wallsplashcompose.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wallsplashcompose.core.di.AppModule
import com.example.wallsplashcompose.data.mapper.toUnsplashModelList
import com.example.wallsplashcompose.data.remote.dto.UnsplashImageDto
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "HomeViewModel"
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val imageRepository: ImageRepository
): ViewModel() {
    val homeImages: StateFlow<PagingData<UnsplashImage>> = imageRepository
        .getHomeImages()
        .catch { exception ->
            exception.printStackTrace()
        }
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        )

    val favImagesIds: StateFlow<List<String>> = imageRepository
        .getFavImageIds()
        .catch { exception ->
            exception.printStackTrace()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun toggleFavStatus(image: UnsplashImage) {
        viewModelScope.launch {
            try {
                imageRepository.toggleFavStatus(image)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}