package com.example.wallsplashcompose.data.repository

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import com.example.wallsplashcompose.domain.repository.Downloader
import java.io.File

class ImageDownloader(
    context: Context
): Downloader {
    private val downloadManager = context
        .getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String, fileName: String?) {
        try {
            val title = fileName ?: "Image"
            val request = DownloadManager
                .Request(url.toUri())
                .setMimeType("image/*")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle(title)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    File.separator + title + ".jpg"
                )
            downloadManager.enqueue(request)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}