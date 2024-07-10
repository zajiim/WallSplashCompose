package com.example.wallsplashcompose.core.di

import com.example.wallsplashcompose.data.remote.UnsplashApiService
import com.example.wallsplashcompose.data.repository.ImageRepositoryImpl
import com.example.wallsplashcompose.domain.repository.ImageRepository
import com.example.wallsplashcompose.utils.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providesUnsplashApiService(): UnsplashApiService {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .build()
            .create(UnsplashApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideImageRepository(
        apiService: UnsplashApiService
    ): ImageRepository {
        return ImageRepositoryImpl(apiService)
    }
}