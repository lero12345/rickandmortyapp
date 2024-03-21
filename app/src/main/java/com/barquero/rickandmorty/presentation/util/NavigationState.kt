package com.barquero.rickandmorty.presentation.util

sealed class NavigationState {
    data class CharacterDetail(val characterId: Int) : NavigationState()
}