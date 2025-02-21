package com.example.wallsplashcompose.presentation.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.presentation.components.CustomTopAppBar
import com.example.wallsplashcompose.presentation.components.EmptyScreen
import com.example.wallsplashcompose.presentation.components.ImageVerticalGrid
import com.example.wallsplashcompose.presentation.home.components.ZoomedImageCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    favoriteImages: LazyPagingItems<UnsplashImage>,
    onImageClick: (String) -> Unit,
    onToggleFavStatus: (UnsplashImage) -> Unit,
    favImageIds: List<String>,
    scrollBehavior: TopAppBarScrollBehavior,
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
                title = "Favorite Images"
            )


            ImageVerticalGrid(
                images = favoriteImages,
                onItemClick = onImageClick,
                onImageLongPress = { image ->
                    activeImage = image
                    showImagePreview = true
                },
                onImagePressEnd = { showImagePreview = false },
                onToggleFavStatus = onToggleFavStatus,
                favImageIds = favImageIds
            )
        }
        ZoomedImageCard(
            modifier = Modifier.padding(24.dp),
            image = activeImage,
            isVisible = showImagePreview
        )
        if(favoriteImages.itemCount == 0) {
            EmptyScreen(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                title = "No Favorite images",
                subtitle = "Give love to your favorite images"
            )
        }
    }
}