package com.example.wallsplashcompose.presentation.home.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.presentation.components.LineScaleProgressIndicator

@Composable
fun DetailsScreen(
    image: UnsplashImage?, modifier: Modifier = Modifier, onBackClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
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

        Image(painter = imagePainter, contentDescription = "Raw image")
    }

}