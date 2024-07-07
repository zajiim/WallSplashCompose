package com.example.wallsplashcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.wallsplashcompose.presentation.main.MainScreen
import com.example.wallsplashcompose.presentation.ui.theme.WallSplashComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WallSplashComposeTheme {
                MainScreen()
            }
        }
    }
}

