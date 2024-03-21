package com.barquero.rickandmorty.data.repository

import com.barquero.rickandmorty.data.api.CharacterInfoApiModel
import com.barquero.rickandmorty.data.datasource.FavoritesLocalDataSource
import kotlinx.coroutines.flow.Flow

class FavoritesRepositoryImpl(
    private val dataSource: FavoritesLocalDataSource
) : FavoritesRepository {

    override fun requestFavoriteCharacters(): Flow<Result<List<CharacterInfoApiModel>>> =
        dataSource.requestFavorites()
}