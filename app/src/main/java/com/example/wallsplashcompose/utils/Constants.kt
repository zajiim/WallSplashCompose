package com.example.wallsplashcompose.utils

import com.example.wallsplashcompose.BuildConfig

object Constants {
    const val API_KEY = BuildConfig.UNSPLASH_API_KEY
    const val BASE_URL = "https://api.unsplash.com"
    const val PER_PAGE_ITEMS = 10
    const val WALLSPLASH_DB = "wallsplash.db"
    const val UNSPLASH_IMAGE_TABLE = "unsplash_image_table"
    const val UNSPLASH_REMOTE_KEYS_TABLE = "unsplash_remote-keys_table"
    const val FAVORITE_IMAGE_TABLE = "favorite_image_table"
}