package com.example.wallsplashcompose.presentation.home.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.presentation.components.ImageCard

@Composable
fun ImageVerticalGrid(
    modifier: Modifier = Modifier,
//    images: List<UnsplashImage?>,
    images: LazyPagingItems<UnsplashImage>,
    onItemClick: (String) -> Unit,
    onImageLongPress: (UnsplashImage?) -> Unit,
    onImagePressEnd: () -> Unit,
    onToggleFavStatus: (UnsplashImage) -> Unit,
    favImageIds: List<String>
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Adaptive(120.dp),
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalItemSpacing = 8.dp
    ) {
        items(count = images.itemCount) { index ->
            val image = images[index]
            ImageCard(
                image = image, modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(onLongPress = {
                        onImageLongPress(image)
                    }, onPress = {
                        awaitRelease()
                        onImagePressEnd()
                    }, onTap = {
                        image?.id?.let { onItemClick(it) }
                    })
                }, onToggleFavStatus = {
                    image?.let {
                        onToggleFavStatus(it)
                    }
                }, isFavorite = favImageIds.contains(image?.id)
            )
        }

    }

}