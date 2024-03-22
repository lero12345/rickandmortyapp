package com.barquero.rickandmorty.domain.usecase

import com.barquero.rickandmorty.data.model.CharacterApiModel
import com.barquero.rickandmorty.data.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

class GetCharactersUseCaseImpl(private val characterRepository: CharacterRepository) :
    GetCharactersUseCase {
    override fun execute(): Flow<Result<CharacterApiModel>> {
        return characterRepository.requestCharacters()
            .catch {
                emit(Result.failure(it))
            }.flowOn(Dispatchers.IO)
    }
}