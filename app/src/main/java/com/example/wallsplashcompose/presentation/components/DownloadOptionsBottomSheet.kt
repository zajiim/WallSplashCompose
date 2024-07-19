package com.example.wallsplashcompose.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadOptionsBottomSheet(
    modifier: Modifier = Modifier,
    isOpen: Boolean,
    sheetState: SheetState,
    onOptionClick: (ImageDownloadOptions) -> Unit,
    options: List<ImageDownloadOptions> = ImageDownloadOptions.entries,
    onDismissRequest: () -> Unit
) {
    if (isOpen) {
        ModalBottomSheet(
            modifier = modifier,
            containerColor = White,
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
                        color = Black
                    )
                }
            }

        }
    }

}

enum class ImageDownloadOptions(val label: String) {
    LOW(label = "Download Low Quality"), MEDIUM(label = "Download Medium Quality"), HIGH(label = "Download High Quality")
}