package com.example.wallsplashcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wallsplashcompose.data.local.dao.FavoriteImageDao
import com.example.wallsplashcompose.data.local.dao.HomeFeedDao
import com.example.wallsplashcompose.data.local.entity.FavoriteImageEntity
import com.example.wallsplashcompose.data.local.entity.UnsplashImageEntity
import com.example.wallsplashcompose.data.local.entity.UnsplashRemoteKeys


@Database(
    entities = [FavoriteImageEntity::class, UnsplashImageEntity::class, UnsplashRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class WallSplashDatabase : RoomDatabase() {
    abstract fun favImagesDao(): FavoriteImageDao

    abstract fun homeFeedDao(): HomeFeedDao
}