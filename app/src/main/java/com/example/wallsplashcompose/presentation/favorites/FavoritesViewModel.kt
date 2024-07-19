package com.example.wallsplashcompose.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: ImageRepository
): ViewModel() {

    val favImages: StateFlow<PagingData<UnsplashImage>> = repository
        .getAllFavImages()
        .catch { exception ->
            exception.printStackTrace()
        }
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        )

    val favImagesIds: StateFlow<List<String>> = repository
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
                repository.toggleFavStatus(image)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}