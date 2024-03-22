package com.barquero.rickandmorty.data.repository

import com.barquero.rickandmorty.data.model.CharacterInfoApiModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun requestFavoriteCharacters(): Flow<Result<List<CharacterInfoApiModel>>>

    suspend fun removeFavorite(characterId: Int)

    suspend fun addFavorite(characterId: Int)
}