package com.example.wallsplashcompose.presentation.home.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import com.example.wallsplashcompose.domain.models.UnsplashImage

@Composable
fun DetailsScreen(
    image: UnsplashImage?, modifier: Modifier = Modifier, onBackClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        val imagePainter = rememberAsyncImagePainter(model = image?.imageUrlRaw)
        IconButton(onClick = { onBackClick() }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "go back")
        }

        Image(painter = imagePainter, contentDescription = "Raw image")
    }

}