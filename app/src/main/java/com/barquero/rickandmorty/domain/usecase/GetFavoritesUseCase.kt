package com.barquero.rickandmorty.domain.usecase

import com.barquero.rickandmorty.data.api.CharacterInfoApiModel
import kotlinx.coroutines.flow.Flow

interface GetFavoritesUseCase {
    suspend fun execute(): Flow<Result<List<CharacterInfoApiModel>>>
}