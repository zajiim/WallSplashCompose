package com.example.wallsplashcompose.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.presentation.home.components.ImageCard
import com.example.wallsplashcompose.presentation.home.components.ImageVerticalGrid

@Composable
fun HomeScreen(
    images: List<UnsplashImage>,
    onImageClick: (String) -> Unit
    ) {

    ImageVerticalGrid(
        images = images,
        onItemClick = onImageClick
    )

}