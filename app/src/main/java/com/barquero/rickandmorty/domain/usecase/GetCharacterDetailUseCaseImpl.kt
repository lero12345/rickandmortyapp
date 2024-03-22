package com.barquero.rickandmorty.domain.usecase

import com.barquero.rickandmorty.data.model.CharacterInfoApiModel
import com.barquero.rickandmorty.data.repository.CharacterDetailRepository

class GetCharacterDetailUseCaseImpl(
    val repository: CharacterDetailRepository
) : GetCharacterDetailUseCase {

    override suspend fun execute(characterId: Int): Result<CharacterInfoApiModel> =
        repository.requestCharacter(characterId)
}