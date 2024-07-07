package com.example.wallsplashcompose.core.navigation

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Favorites : Routes("favorites")
}
