package com.example.wallsplashcompose.presentation.home.details

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateZoomBy
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.wallsplashcompose.R
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.presentation.components.DownloadOptionsBottomSheet
import com.example.wallsplashcompose.presentation.components.ImageDownloadOptions
import com.example.wallsplashcompose.presentation.components.LineScaleProgressIndicator
import com.example.wallsplashcompose.presentation.home.details.components.DetailsTopAppBar
import kotlinx.coroutines.launch
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    image: UnsplashImage?,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onPhotographerClick: (String) -> Unit,
    onDownloadClick: (String, String?) -> Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isDownloadButtonSheetOpen by remember {
        mutableStateOf(false)
    }

    DownloadOptionsBottomSheet(
        isOpen = isDownloadButtonSheetOpen,
        sheetState = sheetState,
        onOptionClick = { option ->
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) isDownloadButtonSheetOpen = false
            }
            val url = when(option) {
                ImageDownloadOptions.LOW -> image?.imageUrlSmall
                ImageDownloadOptions.MEDIUM -> image?.imageUrlRegular
                ImageDownloadOptions.HIGH -> image?.imageUrlRaw
            }

            url?.let {
                onDownloadClick(it, image?.description?.take(10))
                Toast.makeText(context, "Download started", Toast.LENGTH_SHORT).show()
            }
        } ,
        onDismissRequest = { isDownloadButtonSheetOpen = false }
    )


    Box(
        modifier = modifier.fillMaxSize().safeDrawingPadding(),
        contentAlignment = Alignment.Center
    ) {
        BoxWithConstraints(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
            ) {
            var scale by remember {
                mutableFloatStateOf(1f)
            }
            var offset by remember {
                mutableStateOf(Offset.Zero)
            }
            val isImageZoomed: Boolean by remember {
                derivedStateOf { scale != 1f}
            }
            val transformState = rememberTransformableState { zoomChange, panChange, rotationChange ->
                scale = max(scale * zoomChange, 1f)
                val maxX = (constraints.maxWidth * (scale - 1)) / 2
                val maxY = (constraints.maxHeight * (scale - 1)) / 2
                offset = Offset(
                    x = (offset.x + panChange.x).coerceIn(-maxX, maxX),
                    y = (offset.y + panChange.y).coerceIn(-maxY, maxY)
                )
            }
            var isLoading by remember { mutableStateOf(true) }
            var isError by remember { mutableStateOf(false) }
            val imagePainter =
                rememberAsyncImagePainter(model = image?.imageUrlRaw, onState = { imageState ->
                    isLoading = imageState is AsyncImagePainter.State.Loading
                    isError = imageState is AsyncImagePainter.State.Error
                })



            if (isLoading) {
                LineScaleProgressIndicator()
            }

            Image(
                painter = if(isError.not()) imagePainter else painterResource(id = R.drawable.ic_image_error),
                contentDescription = "Raw image",
                modifier = Modifier
                    .transformable(transformState)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = {
                                if (isImageZoomed) {
                                    scale = 1f
                                    offset = Offset.Zero
                                } else {
                                    scope.launch { transformState.animateZoomBy(zoomFactor = 3f) }
                                }
                            }
                        )
                    }
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        translationX = offset.x
                        translationY = offset.y
                    }
            )
        }
        
        DetailsTopAppBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(),
            image = image,
            onBackClick = { onBackClick() },
            onPhotographerClick = onPhotographerClick,
            onDownloadIconClick = { isDownloadButtonSheetOpen = true }
        )
    }

}