package com.example.wallsplashcompose.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.presentation.home.components.CustomTopAppBar
import com.example.wallsplashcompose.presentation.home.components.ImageVerticalGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    images: List<UnsplashImage>,
    onImageClick: (String) -> Unit,
    onSearchClick: () -> Unit
    ) {

    Column {
        CustomTopAppBar(
            scrollBehavior = scrollBehavior,
            onSearchClick = { onSearchClick() },
            title = "Wall Splash"
        )
        
        ImageVerticalGrid(
            images = images,
            onItemClick = onImageClick
        )
    }

}