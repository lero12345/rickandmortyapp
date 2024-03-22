package com.barquero.rickandmorty.domain.usecase

interface AddCharacterFavoriteUseCase {
    suspend fun execute(characterId: Int)
}