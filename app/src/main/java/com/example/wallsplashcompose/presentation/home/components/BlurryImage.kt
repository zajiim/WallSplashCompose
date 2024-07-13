package com.example.wallsplashcompose.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BlurryImage(
    modifier: Modifier = Modifier,
    blurRadius: Dp = 16.dp,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(0.4f))
            .blur(
                radiusX = blurRadius,
                radiusY = blurRadius,
                edgeTreatment = BlurredEdgeTreatment.Unbounded
            )
    )
}