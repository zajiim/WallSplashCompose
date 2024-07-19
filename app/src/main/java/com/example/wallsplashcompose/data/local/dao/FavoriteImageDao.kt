package com.example.wallsplashcompose.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.wallsplashcompose.data.local.entity.FavoriteImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteImageDao {
    @Query("SELECT * FROM favorite_image_table")
    fun getAllFavImages(): PagingSource<Int, FavoriteImageEntity>

    @Upsert
    suspend fun insertFavImage(image: FavoriteImageEntity)

    @Delete
    suspend fun deleteFavImage(image: FavoriteImageEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_image_table WHERE id = :id)")
    suspend fun isImageFav(id: String): Boolean

    @Query("SELECT id FROM favorite_image_table")
    fun getFavImageByIds(): Flow<List<String>>

}