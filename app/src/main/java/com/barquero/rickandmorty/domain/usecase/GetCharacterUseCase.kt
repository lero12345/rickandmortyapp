package com.barquero.rickandmorty.domain.usecase

import com.barquero.rickandmorty.data.api.CharacterApiModel
import kotlinx.coroutines.flow.Flow

interface GetCharactersUseCase {
    fun execute(): Flow<Result<CharacterApiModel>>
}