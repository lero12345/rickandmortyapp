package com.barquero.rickandmorty.presentation.ui.pages

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.barquero.rickandmorty.R
import com.barquero.rickandmorty.data.api.CharacterInfoApiModel
import com.barquero.rickandmorty.presentation.ui.atomic.organisms.CharactersList
import com.barquero.rickandmorty.presentation.ui.atomic.organisms.EmptyStateView
import com.barquero.rickandmorty.presentation.ui.atomic.organisms.LoaderFullScreen
import com.barquero.rickandmorty.presentation.ui.charactersfeed.CharactersUiState
import com.barquero.rickandmorty.presentation.ui.charactersfeed.CharactersViewModel
import com.barquero.rickandmorty.presentation.ui.main.MainRouter
import com.barquero.rickandmorty.presentation.util.NavigationState

@Composable
fun CharactersPage(
    mainRouter: MainRouter,
    viewModel: CharactersViewModel
) {
    val charactersData = viewModel.characterFeedState.collectAsState()
    val navigationState by viewModel.navigationState.collectAsState(null)

    LaunchedEffect(key1 = charactersData) {
        viewModel.getCharacterList()
    }

    LaunchedEffect(key1 = navigationState) {
        when (val navState = navigationState) {
            is NavigationState.CharacterDetail -> mainRouter.navigateToCharacterDetails(navState.characterId)
            else -> Unit
        }
    }

    val lazyGridState = rememberLazyGridState()

    CharacterFeedScreen(
        viewModel.charactersListData.results.orEmpty(),
        lazyGridState,
        charactersData.value,
        viewModel::onCharacterClicked
    )
}


@Composable
private fun CharacterFeedScreen(
    characterList: List<CharacterInfoApiModel>,
    lazyGridState: LazyGridState,
    uiState: CharactersUiState,
    onCharacterClick: (characterId: Int) -> Unit
) {
    Surface {
        when (uiState) {
            is CharactersUiState.FAILURE -> EmptyStateView(titleRes = R.string.no_characters_found)
            CharactersUiState.LOADING -> LoaderFullScreen()
            CharactersUiState.SUCCESS -> CharactersList(
                characterList = characterList,
                lazyGridState = lazyGridState,
                onCharacterClick = onCharacterClick
            )

            else -> Unit
        }
    }
}
