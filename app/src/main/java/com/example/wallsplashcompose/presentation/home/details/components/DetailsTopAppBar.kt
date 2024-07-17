package com.example.wallsplashcompose.presentation.home.details.components

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wallsplashcompose.R
import com.example.wallsplashcompose.domain.models.UnsplashImage

@Composable
fun DetailsTopAppBar(
    modifier: Modifier = Modifier,
    image: UnsplashImage?,
    onBackClick: () -> Unit,
    onPhotographerClick: (String) -> Unit,
    onDownloadIconClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "go back")
        }

        AsyncImage(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape),
            model = image?.photographerProfileImage,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier
                .clickable {
                    image?.let {
                        onPhotographerClick(it.photographerProfileLink)
                    }

                }) {

            Text(
                text = image?.photographerName ?: "",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = image?.photographerUsername ?: "",
                style = MaterialTheme.typography.titleSmall
            )

        }
        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = { onDownloadIconClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_download),
                contentDescription = "Download the image",
                tint = MaterialTheme.colorScheme.onBackground
                )

        }
    }

}