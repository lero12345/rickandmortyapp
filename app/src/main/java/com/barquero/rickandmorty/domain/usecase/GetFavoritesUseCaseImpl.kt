package com.barquero.rickandmorty.domain.usecase

import com.barquero.rickandmorty.data.api.CharacterInfoApiModel
import com.barquero.rickandmorty.data.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class GetFavoritesUseCaseImpl(
    private val favoritesRepository: FavoritesRepository
) : GetFavoritesUseCase {

    override suspend fun execute(): Flow<Result<List<CharacterInfoApiModel>>> {
        return favoritesRepository.requestFavoriteCharacters()
            .catch {
                emit(Result.failure(it))
            }
    }
}