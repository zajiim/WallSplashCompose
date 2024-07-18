package com.example.wallsplashcompose.presentation.search

import androidx.lifecycle.ViewModel
import com.example.wallsplashcompose.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ImageRepository
): ViewModel() {
}