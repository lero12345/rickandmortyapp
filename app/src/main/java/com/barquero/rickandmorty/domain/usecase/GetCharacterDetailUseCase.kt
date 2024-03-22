package com.barquero.rickandmorty.domain.usecase

import com.barquero.rickandmorty.data.model.CharacterInfoApiModel

interface GetCharacterDetailUseCase {
    suspend fun execute(characterId: Int): Result<CharacterInfoApiModel>
}