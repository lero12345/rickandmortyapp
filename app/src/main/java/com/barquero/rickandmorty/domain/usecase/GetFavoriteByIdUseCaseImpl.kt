package com.barquero.rickandmorty.domain.usecase

import com.barquero.rickandmorty.data.repository.FavoritesByIdRepository

class GetFavoriteByIdUseCaseImpl(
    private val favoritesRepository: FavoritesByIdRepository
) : GetFavoriteByIdUseCase {
    override suspend fun execute(characterId: Int): Result<Boolean> {
        return favoritesRepository.requestCharacterBy(characterId)
    }
}