package com.barquero.rickandmorty.presentation.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.barquero.rickandmorty.R
import com.barquero.rickandmorty.presentation.ui.atomic.organisms.CharacterItemPlaceholder
import com.barquero.rickandmorty.presentation.ui.characterdetail.CharacterDetailUiState
import com.barquero.rickandmorty.presentation.ui.characterdetail.CharacterDetailViewModel

@Composable
fun CharacterDetailPage(
    mainNavController: NavHostController,
    viewModel: CharacterDetailViewModel
) {
    val state by viewModel.uiState.collectAsState()
    CharacterDetailScreen(
        state,
        appNavHostController = mainNavController,
        onFavoriteClick = viewModel::onFavoriteClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    state: CharacterDetailUiState,
    onFavoriteClick: () -> Unit,
    appNavHostController: NavHostController
) {

    val favoriteCharacterIcon =
        if (state.isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onFavoriteClick() }) {
                Image(
                    painter = painterResource(id = favoriteCharacterIcon),
                    contentDescription = null,
                    Modifier.size(24.dp)
                )
            }
        },
        topBar = {
            TopAppBar(
                title = { Text(text = "Overview") },
                navigationIcon = {
                    IconButton(onClick = { appNavHostController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize(1f)
                .padding(paddingValues)
        ) {
            SubcomposeAsyncImage(
                model = state.image,
                loading = { CharacterItemPlaceholder() },
                error = { CharacterItemPlaceholder() },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth(1f)
            )

            Text(
                text = state.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
                    .fillMaxWidth(1f),
            )

            Text(
                text = state.status,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .fillMaxWidth(1f),
            )
        }
    }
}