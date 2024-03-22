package com.barquero.rickandmorty.data.repository

import com.barquero.rickandmorty.data.model.CharacterApiModel
import com.barquero.rickandmorty.data.datasource.CharacterRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepositoryImpl(
    private val dataSource: CharacterRemoteDataSource
) : CharacterRepository {

    override fun requestCharacters(): Flow<Result<CharacterApiModel>> {
        return flow {
            emit(dataSource.invoke())
        }
    }
}