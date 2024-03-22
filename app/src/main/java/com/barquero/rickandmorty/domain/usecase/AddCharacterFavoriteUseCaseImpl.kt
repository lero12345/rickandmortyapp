package com.barquero.rickandmorty.domain.usecase

import com.barquero.rickandmorty.data.repository.FavoritesRepository

class AddCharacterFavoriteUseCaseImpl(
    val repository: FavoritesRepository
) : AddCharacterFavoriteUseCase {

    override suspend fun execute(characterId: Int) {
        return repository.addFavorite(characterId)
    }
}