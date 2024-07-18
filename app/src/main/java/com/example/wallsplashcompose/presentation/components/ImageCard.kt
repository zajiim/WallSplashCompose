package com.example.wallsplashcompose.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.presentation.home.components.FavoriteButton

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    image: UnsplashImage?,
    isFavorite: Boolean,
    onToggleFavStatus: () -> Unit
) {
    val unsplashImage =
        ImageRequest.Builder(LocalContext.current).data(image?.imageUrlSmall).crossfade(true)
            .build()
    val aspectRatio: Float by remember {
        derivedStateOf { (image?.width?.toFloat() ?: 1f) / (image?.height?.toFloat() ?: 1f) }
    }
    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
            .then(modifier)
    ) {
        Box {
            AsyncImage(
                model = unsplashImage,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            
            FavoriteButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                isFavorite = isFavorite,
                onFavClick = onToggleFavStatus
            )
        }

    }

}