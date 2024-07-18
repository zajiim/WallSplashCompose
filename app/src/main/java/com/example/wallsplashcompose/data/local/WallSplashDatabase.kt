package com.example.wallsplashcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wallsplashcompose.data.local.entity.FavoriteImageEntity


@Database(
    entities = [FavoriteImageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WallSplashDatabase: RoomDatabase() {
    abstract fun favImagesDao(): FavoriteImageDao
}