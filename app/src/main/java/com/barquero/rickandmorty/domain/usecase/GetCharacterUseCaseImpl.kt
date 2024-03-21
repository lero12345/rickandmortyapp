package com.barquero.rickandmorty.domain.usecase

import com.barquero.rickandmorty.data.api.CharacterApiModel
import com.barquero.rickandmorty.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class GetCharactersUseCaseImpl(private val characterRepository: CharacterRepository) :
    GetCharactersUseCase {
    override fun execute(): Flow<Result<CharacterApiModel>> {
        return characterRepository.requestCharacters()
            .catch {
                emit(Result.failure(it))
            }
    }
}