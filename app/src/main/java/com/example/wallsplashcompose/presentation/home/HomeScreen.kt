package com.example.wallsplashcompose.presentation.home

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wallsplashcompose.data.remote.dto.UnsplashImageDto
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.presentation.home.components.ImageCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    images: List<UnsplashImage>
    ) {

    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            images.forEach { image ->
                ImageCard(image = image)
            }
        }
    }

}