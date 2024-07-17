package com.example.wallsplashcompose.domain.repository

interface Downloader {
    fun downloadFile(url: String, fileName: String?)
}