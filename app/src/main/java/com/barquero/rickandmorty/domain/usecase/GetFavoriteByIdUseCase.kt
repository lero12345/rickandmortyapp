package com.barquero.rickandmorty.domain.usecase

interface GetFavoriteByIdUseCase {
    suspend fun execute(characterId: Int): Result<Boolean>
}