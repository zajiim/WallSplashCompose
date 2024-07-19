package com.example.wallsplashcompose.presentation.favorites

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.presentation.components.EmptyScreen
import com.example.wallsplashcompose.presentation.home.components.CustomTopAppBar
import com.example.wallsplashcompose.presentation.home.components.ImageVerticalGrid
import com.example.wallsplashcompose.presentation.home.components.ZoomedImageCard
import com.example.wallsplashcompose.utils.searchKeywords
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
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
                title = "Favorite Images",
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "go back"
                        )
                    }
                }
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