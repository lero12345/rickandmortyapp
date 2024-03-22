package com.barquero.rickandmorty.domain.usecase

import com.barquero.rickandmorty.data.model.CharacterInfoApiModel
import com.barquero.rickandmorty.data.repository.CharacterDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharacterDetailUseCaseImpl(
    val repository: CharacterDetailRepository
) : GetCharacterDetailUseCase {

    override suspend fun execute(characterId: Int): Result<CharacterInfoApiModel> =
        withContext(Dispatchers.IO) {
            repository.requestCharacter(characterId)
        }
}