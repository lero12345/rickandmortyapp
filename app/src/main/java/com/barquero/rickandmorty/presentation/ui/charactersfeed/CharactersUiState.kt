package com.barquero.rickandmorty.presentation.ui.charactersfeed

sealed class CharactersUiState {
    data object START : CharactersUiState()
    data object LOADING : CharactersUiState()
    data object SUCCESS : CharactersUiState()
    data class FAILURE(val message: String) : CharactersUiState()
}