package com.barquero.rickandmorty.data.datasource

import com.barquero.rickandmorty.data.api.CharacterInfoApiModel
import com.barquero.rickandmorty.data.util.DbHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoritesLocalDataSource(
    private val dbHelper: DbHelper
) {
    fun requestFavorites(): Flow<Result<List<CharacterInfoApiModel>>> = flow {
        val characters = dbHelper.getAllFavorites()
        if (characters.isNotEmpty()) {
            emit(Result.success(characters))
        } else {
            emit(Result.failure(Exception("No characters in local db")))
        }
    }
}