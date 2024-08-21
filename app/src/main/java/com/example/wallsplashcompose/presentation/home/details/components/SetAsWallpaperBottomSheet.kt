package com.example.wallsplashcompose.presentation.home.details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetAsWallpaperBottomSheet(
    modifier: Modifier = Modifier,
    isOpen: Boolean,
    sheetState: SheetState,
    onOptionClick: (SetAsWallpaperOptions) -> Unit,
    options: List<SetAsWallpaperOptions> = SetAsWallpaperOptions.entries,
    onDismissRequest: () -> Unit
) {
    if (isOpen) {
        ModalBottomSheet(
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.background,
            sheetState = sheetState,
            onDismissRequest = { onDismissRequest() }
        ) {
            options.forEach { option ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOptionClick(option) }
                    .padding(16.dp),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = option.label,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }

        }
    }
}



enum class SetAsWallpaperOptions(val label: String) {
    LOCK_SCREEN(label = "Set as LockScreen Wallpaper"),
    HOME_SCREEN(label = "Set as HomeScreen Wallpaper"),
    BOTH(label = "Set as Both")
}