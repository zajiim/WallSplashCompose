package com.example.wallsplashcompose.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    images: String
    ) {

    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            Text(text = images)
        }
    }
}