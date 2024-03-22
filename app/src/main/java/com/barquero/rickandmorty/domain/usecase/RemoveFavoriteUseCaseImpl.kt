package com.barquero.rickandmorty.domain.usecase

import com.barquero.rickandmorty.data.repository.FavoritesRepository

class RemoveFavoriteUseCaseImpl(
    val repository: FavoritesRepository
) : RemoveFavoriteUseCase {
    override suspend fun removeFavorite(characterId: Int) {
        repository.removeFavorite(characterId)
    }
}