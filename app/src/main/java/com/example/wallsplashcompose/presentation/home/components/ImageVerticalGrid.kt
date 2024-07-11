package com.example.wallsplashcompose.presentation.home.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.wallsplashcompose.domain.models.UnsplashImage

@Composable
fun ImageVerticalGrid(
    modifier: Modifier = Modifier,
    images: List<UnsplashImage?>,
    onItemClick: (String) -> Unit,
    onImageLongPress: (UnsplashImage?) -> Unit,
    onImagePressEnd: () -> Unit,
//    onImageDragStart: (UnsplashImage?) -> Unit,
//    onImageDragEnd: () -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Adaptive(120.dp),
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalItemSpacing = 8.dp
        ) {
        items(images) { image ->
            ImageCard(
                image = image,
                modifier = Modifier
                    .clickable {
                        image?.id?.let { onItemClick(it) }
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = { onImageLongPress(image) },
                            onPress = {
                                awaitRelease()
                                onImagePressEnd()
                            }
                        ) {

                        }

//                        detectDragGestures(
//                            onDragStart = {onImageDragStart(image)},
//                            onDragCancel = {onImageDragEnd()},
//                            onDragEnd = {onImageDragEnd()},
//                            onDrag = {_, _ ->})
                    })
        }

    }

}