package com.barquero.rickandmorty.data.repository

import com.barquero.rickandmorty.data.datasource.FavoritesLocalDataSource

class FavoritesByIdRepositoryImpl(
    private val dataSource: FavoritesLocalDataSource
) : FavoritesByIdRepository {
    override suspend fun requestCharacterBy(characterId: Int): Result<Boolean> {
        return dataSource.checkCharacterFavoriteStatus(characterId)
    }
}