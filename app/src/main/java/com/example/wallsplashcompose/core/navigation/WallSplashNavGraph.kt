package com.example.wallsplashcompose.core.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wallsplashcompose.presentation.favorites.FavoritesScreen
import com.example.wallsplashcompose.presentation.home.HomeScreen
import com.example.wallsplashcompose.presentation.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallSplashNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    val viewModel = viewModel<HomeViewModel>()
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        modifier = modifier
    ) {
        composable(route = Routes.Home.route) {
            HomeScreen(
                scrollBehavior = scrollBehavior,
                images = viewModel.images,
                onImageClick = viewModel::onImageClick,
                onSearchClick = viewModel::onSearchClick,
                modifier = modifier
            )
        }
        composable(route = Routes.Favorites.route) {
            FavoritesScreen()
        }
    }

}