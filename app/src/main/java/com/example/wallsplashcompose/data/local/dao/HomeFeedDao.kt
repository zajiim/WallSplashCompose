package com.example.wallsplashcompose.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.wallsplashcompose.data.local.entity.UnsplashImageEntity
import com.example.wallsplashcompose.data.local.entity.UnsplashRemoteKeys

@Dao
interface HomeFeedDao {

    @Query("SELECT * FROM unsplash_image_table")
    fun getAllHomeImages(): PagingSource<Int, UnsplashImageEntity>

    @Upsert
    suspend fun insertHomeImages(images: List<UnsplashImageEntity>)

    @Query("DELETE FROM unsplash_image_table")
    suspend fun deleteHomeImages()


    @Query("SELECT * FROM `unsplash_remote-keys_table` WHERE id = :id")
    suspend fun getRemoteKeys(id: String): UnsplashRemoteKeys

    @Upsert
    suspend fun insertAllRemoteKeys(remoteKeys: List<UnsplashRemoteKeys>)

    @Query("DELETE FROM `unsplash_remote-keys_table`")
    suspend fun deleteAllRemoteKeys()

}