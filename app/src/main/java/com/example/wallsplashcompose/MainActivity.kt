package com.example.wallsplashcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.wallsplashcompose.presentation.main.MainScreen
import com.example.wallsplashcompose.presentation.ui.theme.WallSplashComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            WallSplashComposeTheme {
                MainScreen()
            }
        }
    }
}

