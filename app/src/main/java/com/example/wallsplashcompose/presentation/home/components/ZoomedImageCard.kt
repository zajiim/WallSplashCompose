package com.example.wallsplashcompose.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.skydoves.cloudy.Cloudy

@Composable
fun ZoomedImageCard(
    modifier: Modifier = Modifier,
    image: UnsplashImage?,
    isVisible: Boolean
) {
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(image?.imageUrlRegular)
        .crossfade(true)
        .build()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
        ) {
        if (isVisible) {
            Cloudy(
                modifier = Modifier.fillMaxSize(),
                radius = 25
            ) {}
        }
            AnimatedVisibility(
                visible = isVisible,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Card(modifier = modifier) {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        model = imageRequest,
                        contentDescription = null
                    )

                }
            }
        }
}