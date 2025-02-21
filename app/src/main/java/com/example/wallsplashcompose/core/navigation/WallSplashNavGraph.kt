package com.example.wallsplashcompose.core.navigation

import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wallsplashcompose.presentation.favorites.FavoritesScreen
import com.example.wallsplashcompose.presentation.favorites.FavoritesViewModel
import com.example.wallsplashcompose.presentation.home.HomeScreen
import com.example.wallsplashcompose.presentation.home.HomeViewModel
import com.example.wallsplashcompose.presentation.home.details.DetailsImageViewModel
import com.example.wallsplashcompose.presentation.home.details.DetailsScreen
import com.example.wallsplashcompose.presentation.profile.ProfileScreen
import com.example.wallsplashcompose.presentation.search.SearchScreen
import com.example.wallsplashcompose.presentation.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallSplashNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        modifier = modifier,
        enterTransition = {
            fadeIn(animationSpec = tween(400)) + slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(400)) + slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End
            )
        }
    ) {
        composable(route = Routes.Home.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val homeImages = homeViewModel.homeImages.collectAsLazyPagingItems()
            val favImageIds by homeViewModel.favImagesIds.collectAsStateWithLifecycle()
            HomeScreen(
                scrollBehavior = scrollBehavior,
                images = homeImages,
                onImageClick = { imageId ->
                    navController.navigate(Routes.DetailsScreen(imageId).route)
                },
                onSearchClick = { navController.navigate(Routes.Search.route) },
                onToggleFavStatus = homeViewModel::toggleFavStatus,
                favImageIds = favImageIds
                )
        }
        composable(route = Routes.Favorites.route) {
            val favoritesViewModel: FavoritesViewModel = hiltViewModel()
            val favImages = favoritesViewModel.favImages.collectAsLazyPagingItems()
            val favImageIds by favoritesViewModel.favImagesIds.collectAsStateWithLifecycle()
            FavoritesScreen(
                favoriteImages = favImages,
                onImageClick = { imageId ->
                    navController.navigate(Routes.DetailsScreen(imageId).route)
                },
                onToggleFavStatus = favoritesViewModel::toggleFavStatus,
                favImageIds = favImageIds,
                scrollBehavior = scrollBehavior
            )
        }
        composable(route = Routes.Search.route) {
            val searchViewModel: SearchViewModel = hiltViewModel()
            val searchImages = searchViewModel.searchImages.collectAsLazyPagingItems()
            val favImageIds by searchViewModel.favImagesIds.collectAsStateWithLifecycle()
            SearchScreen(
                onBackClick = { navController.navigateUp() },
                onImageClick = { imageId ->
                    navController.navigate(Routes.DetailsScreen(imageId).route)
                },
                onSearch = searchViewModel::fetchSearchImages,
                searchImages = searchImages,
                onToggleFavStatus = searchViewModel::toggleFavStatus,
                favImageIds = favImageIds
            )
        }
        composable(
            route = Routes.DetailsScreen("{imageId}").route,
            arguments = listOf(navArgument("imageId") { type = NavType.StringType })
        ) { /*navBackStackEntry ->
            val imageId = navBackStackEntry.arguments?.getString("imageId") ?: ""*/
            val detailsImageViewModel: DetailsImageViewModel = hiltViewModel()
            DetailsScreen(
                image = detailsImageViewModel.image,
                onBackClick = { navController.navigateUp() },
                onPhotographerClick = { profileLink ->
                    val encodedProfileLink = Uri.encode(profileLink)
                    val route = Routes.ProfileScreen(encodedProfileLink).route
                    Log.d("Navigation", "Constructed route: $route")
                    navController.navigate(route)
                },
                onDownloadClick = { url, title ->
                    detailsImageViewModel.downloadImage(url, title)
                },
                onSetAsWallpaperClick = { image, type ->
                    detailsImageViewModel.setWallpaper(image, type)
                }
            )
        }

        composable(
            route = Routes.ProfileScreen("{encodedProfileLink}").route,
            arguments = listOf(navArgument("encodedProfileLink") { type = NavType.StringType })
        ) { backStackEntry ->
            val encodedProfileLink = backStackEntry.arguments?.getString("encodedProfileLink") ?: ""
            val decodedProfileLink = Uri.decode(encodedProfileLink)
            Log.e("Navigation", "WallSplashNavGraph: ===> End $decodedProfileLink")
            ProfileScreen(profileLink = decodedProfileLink, onBackClick = { navController.navigateUp() })
        }

    }

}