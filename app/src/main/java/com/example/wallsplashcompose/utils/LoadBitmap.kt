package com.example.wallsplashcompose.utils

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

suspend fun loadBitmap(url: String, context: Context): Bitmap? {
    val imageLoader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(url)
        .allowHardware(false)
        .build()

    val result = imageLoader.execute(request)
    return (result as? SuccessResult)?.drawable?.toBitmap()
}