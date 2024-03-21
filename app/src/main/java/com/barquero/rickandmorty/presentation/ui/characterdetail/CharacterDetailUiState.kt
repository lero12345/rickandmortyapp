package com.barquero.rickandmorty.presentation.ui.characterdetail

data class CharacterDetailUiState (
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val image: String = "",
    val isFavorite: Boolean = false
)