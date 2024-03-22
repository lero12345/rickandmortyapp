package com.barquero.rickandmorty.domain.usecase

import com.barquero.rickandmorty.data.model.CharacterApiModel
import kotlinx.coroutines.flow.Flow

interface GetCharactersUseCase {
    fun execute(): Flow<Result<CharacterApiModel>>
}