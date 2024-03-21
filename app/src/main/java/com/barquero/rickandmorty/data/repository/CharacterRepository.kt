package com.barquero.rickandmorty.data.repository

import com.barquero.rickandmorty.data.api.CharacterApiModel
import com.barquero.rickandmorty.data.datasource.CharacterRemoteDataSource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun requestCharacters(): Flow<Result<CharacterApiModel>>
}
