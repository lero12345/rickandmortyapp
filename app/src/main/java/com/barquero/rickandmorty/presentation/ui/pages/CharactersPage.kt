package com.barquero.rickandmorty.presentation.ui.pages

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.barquero.rickandmorty.R
import com.barquero.rickandmorty.data.api.CharacterInfoApiModel
import com.barquero.rickandmorty.presentation.ui.atomic.organisms.CharactersList
import com.barquero.rickandmorty.presentation.ui.atomic.organisms.EmptyStateView
import com.barquero.rickandmorty.presentation.ui.atomic.organisms.LoaderFullScreen
import com.barquero.rickandmorty.presentation.ui.charactersfeed.CharactersUiState
import com.barquero.rickandmorty.presentation.ui.charactersfeed.CharactersViewModel
import com.barquero.rickandmorty.presentation.ui.main.MainRouter

@Composable
fun CharactersPage(
    mainRouter: MainRouter,
    viewModel: CharactersViewModel
) {
    val charactersData = viewModel.characterFeedState.collectAsState()

    LaunchedEffect(key1 = charactersData) {
        viewModel.getCharacterList()
    }

    val lazyGridState = rememberLazyGridState()

    CharacterFeedScreen(
        viewModel.charactersListData.results.orEmpty(),
        lazyGridState,
        charactersData.value
    )
}


@Composable
private fun CharacterFeedScreen(
    characterList: List<CharacterInfoApiModel>,
    lazyGridState: LazyGridState,
    uiState: CharactersUiState
) {
    Surface {
        when (uiState) {
            is CharactersUiState.START -> {

            }

            is CharactersUiState.FAILURE -> EmptyStateView(titleRes = R.string.no_movies_found)
            CharactersUiState.LOADING -> LoaderFullScreen()
            CharactersUiState.SUCCESS -> CharactersList(
                characterList = characterList,
                lazyGridState = lazyGridState
            )
        }
    }
}
