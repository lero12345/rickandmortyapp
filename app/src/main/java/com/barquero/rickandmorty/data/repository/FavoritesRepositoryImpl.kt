package com.barquero.rickandmorty.data.repository

import com.barquero.rickandmorty.data.model.CharacterInfoApiModel
import com.barquero.rickandmorty.data.datasource.FavoritesLocalDataSource
import kotlinx.coroutines.flow.Flow

class FavoritesRepositoryImpl(
    private val dataSource: FavoritesLocalDataSource
) : FavoritesRepository {

    override suspend fun requestFavoriteCharacters(): Flow<Result<List<CharacterInfoApiModel>>> =
        dataSource.requestFavorites()

    override suspend fun removeFavorite(characterId: Int) {
        dataSource.removeFavorite(characterId)
    }

    override suspend fun addFavorite(characterId: Int) {
        dataSource.addFavoriteCharacter(CharacterInfoApiModel(id = characterId))
    }
}