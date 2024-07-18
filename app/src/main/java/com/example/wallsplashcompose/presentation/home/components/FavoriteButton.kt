package com.example.wallsplashcompose.presentation.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onFavClick: () -> Unit
) {
    FilledIconToggleButton(
        modifier = modifier,
        checked = isFavorite,
        onCheckedChange = { onFavClick() },
        colors = IconButtonDefaults.filledIconToggleButtonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
        )
    ) {
        if (isFavorite) {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
        } else {
            Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null)
        }

    }

}