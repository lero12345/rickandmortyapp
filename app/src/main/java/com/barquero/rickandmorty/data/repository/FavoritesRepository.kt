package com.barquero.rickandmorty.data.repository

import com.barquero.rickandmorty.data.api.CharacterInfoApiModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun requestFavoriteCharacters(): Flow<Result<List<CharacterInfoApiModel>>>
}