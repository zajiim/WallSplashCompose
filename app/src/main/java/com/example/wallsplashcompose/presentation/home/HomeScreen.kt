package com.example.wallsplashcompose.presentation.home

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wallsplashcompose.data.remote.dto.UnsplashImageDto
import com.example.wallsplashcompose.domain.models.UnsplashImage

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    images: List<UnsplashImage>
    ) {

//    LazyColumn(modifier = modifier.fillMaxSize()) {
//        item {
//            images.forEach { image ->
//                Text(text = image.user.name)
//                Text(text = image.user.username)
//                Text(text = image.description ?: "")
//            }
//        }
//    }
    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        images.forEach { image ->
            Text(text = image.photographerUsername)
            Text(text = image.photographerProfileLink)
            Text(text = image.description ?: "")
            Text(text = image.blurHash ?: "")
        }
    }
}