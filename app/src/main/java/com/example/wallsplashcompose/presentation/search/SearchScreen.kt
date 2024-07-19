package com.example.wallsplashcompose.presentation.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.wallsplashcompose.domain.models.UnsplashImage
import com.example.wallsplashcompose.presentation.components.ImageVerticalGrid
import com.example.wallsplashcompose.presentation.home.components.ZoomedImageCard
import com.example.wallsplashcompose.utils.searchKeywords
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    searchImages: LazyPagingItems<UnsplashImage>,
    onImageClick: (String) -> Unit,
    onSearch: (String) -> Unit,
    onToggleFavStatus: (UnsplashImage) -> Unit,
    favImageIds: List<String>
) {
    val focusRequester = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var showImagePreview by remember {
        mutableStateOf(false)
    }
    var activeImage by remember {
        mutableStateOf<UnsplashImage?>(null)
    }

    var query by remember {
        mutableStateOf("")
    }
    var isSuggestionChipsVisible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        delay(500)
        focusRequester.requestFocus()
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            SearchBar(modifier = Modifier
                .padding(vertical = 10.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { isSuggestionChipsVisible = it.isFocused },
                query = query,
                onQueryChange = { query = it },
                placeholder = { Text(text = "Search") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search, contentDescription = "search button"
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        if (query.isNotEmpty()) query = "" else onBackClick()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Close, contentDescription = "close button"
                        )

                    }
                },
                onSearch = {
                    onSearch(query)
                    keyboardController?.hide()
                    focusManager.clearFocus()
                },
                active = false,
                onActiveChange = {

                },
                content = {

                })

            AnimatedVisibility(visible = isSuggestionChipsVisible) {
                LazyRow(
                    contentPadding = PaddingValues(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val randomizeKeywords = searchKeywords.shuffled()
                    items(randomizeKeywords) { searchKeyword ->
                        SuggestionChip(onClick = {
                            query = searchKeyword
                            onSearch(query)
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        },
                            label = { Text(text = searchKeyword) },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                }
            }

            ImageVerticalGrid(images = searchImages,
                onItemClick = onImageClick,
                onImageLongPress = { image ->
                    activeImage = image
                    showImagePreview = true
                },
                onImagePressEnd = { showImagePreview = false },
                onToggleFavStatus = onToggleFavStatus,
                favImageIds = favImageIds
            )
        }
        ZoomedImageCard(
            modifier = Modifier.padding(24.dp), image = activeImage, isVisible = showImagePreview
        )
    }

}