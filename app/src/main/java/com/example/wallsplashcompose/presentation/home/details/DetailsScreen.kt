package com.example.wallsplashcompose.presentation.home.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.wallsplashcompose.R
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.presentation.components.LineScaleProgressIndicator
import kotlin.math.max

@Composable
fun DetailsScreen(
    image: UnsplashImage?, modifier: Modifier = Modifier, onBackClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        BoxWithConstraints {
            var scale by remember {
                mutableFloatStateOf(1f)
            }
            var offset by remember {
                mutableStateOf(Offset.Zero)
            }
            val transformState = rememberTransformableState { zoomChange, panChange, rotationChange ->
                scale = max(scale * zoomChange, 1f)
                val maxX = (constraints.maxWidth * (scale - 1)) / 2
                val maxY = (constraints.maxHeight * (scale - 1)) / 2
                offset = Offset(
                    x = (offset.x + panChange.x).coerceIn(-maxX, maxX),
                    y = (offset.y + panChange.y).coerceIn(-maxY, maxY)
                )
            }
            var isLoading by remember { mutableStateOf(true) }
            var isError by remember { mutableStateOf(false) }
            val imagePainter =
                rememberAsyncImagePainter(model = image?.imageUrlRaw, onState = { imageState ->
                    isLoading = imageState is AsyncImagePainter.State.Loading
                    isError = imageState is AsyncImagePainter.State.Error
                })

            IconButton(onClick = { onBackClick() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "go back")
            }

            if (isLoading) {
                LineScaleProgressIndicator()
            }

            Image(
                painter = if(isError.not()) imagePainter else painterResource(id = R.drawable.ic_image_error),
                contentDescription = "Raw image",
                modifier = Modifier
                    .transformable(transformState)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        translationX = offset.x
                        translationY = offset.y
                    }
            )
        }
    }

}