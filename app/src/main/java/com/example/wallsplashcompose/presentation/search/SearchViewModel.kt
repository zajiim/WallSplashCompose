package com.example.wallsplashcompose.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ImageRepository,
) : ViewModel() {

    private val _searchImages = MutableStateFlow<PagingData<UnsplashImage>>(PagingData.empty())
    val searchImages = _searchImages


    fun fetchSearchImages(query: String) {
        viewModelScope.launch {
            try {
                repository
                    .searchImage(query)
                    .cachedIn(viewModelScope)
                    .collect { _searchImages.value = it }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

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