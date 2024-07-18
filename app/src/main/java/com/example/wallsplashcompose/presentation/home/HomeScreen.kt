package com.example.wallsplashcompose.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.presentation.home.components.CustomTopAppBar
import com.example.wallsplashcompose.presentation.home.components.ImageVerticalGrid
import com.example.wallsplashcompose.presentation.home.components.ZoomedImageCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    images: List<UnsplashImage>,
    onImageClick: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    var showImagePreview by remember {
        mutableStateOf(false)
    }
    var activeImage by remember {
        mutableStateOf<UnsplashImage?>(null)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            CustomTopAppBar(
                scrollBehavior = scrollBehavior,
                onSearchClick = { onSearchClick() },
                title = "Wall Splash"
            )

//            ImageVerticalGrid(
//                images = images,
//                onItemClick = onImageClick,
//                onImageLongPress = { image ->
//                    activeImage = image
//                    showImagePreview = true
//                },
//                onImagePressEnd = { showImagePreview = false }
//            )
        }
        ZoomedImageCard(
            modifier = Modifier.padding(24.dp),
            image = activeImage,
            isVisible = showImagePreview
        )
    }

}