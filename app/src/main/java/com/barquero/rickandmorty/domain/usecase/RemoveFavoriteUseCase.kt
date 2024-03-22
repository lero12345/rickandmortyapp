package com.barquero.rickandmorty.domain.usecase

interface RemoveFavoriteUseCase {
    suspend fun removeFavorite(characterId: Int)
}