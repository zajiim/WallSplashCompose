package com.example.wallsplashcompose.presentation.home.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.wallsplashcompose.domain.models.UnsplashImage

@Composable
fun ImageCard(
    modifier: Modifier = Modifier, image: UnsplashImage?
) {
    val unsplashImage =
        ImageRequest.Builder(LocalContext.current).data(image?.imageUrlSmall).crossfade(true)
            .build()
    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .then(modifier)
    ) {
        AsyncImage(
            model = unsplashImage,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

    }

}