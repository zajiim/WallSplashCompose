package com.example.wallsplashcompose.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val route: String) {
    @Serializable
    data object Home : Routes("home")
    @Serializable
    data object Favorites : Routes("favorites")
    @Serializable
    data object Search : Routes("search")
    @Serializable
    data class DetailsScreen(val imageId: String): Routes("image/$imageId")
    @Serializable
    data class ProfileScreen(val profileId: String): Routes("profile/$profileId")
}
