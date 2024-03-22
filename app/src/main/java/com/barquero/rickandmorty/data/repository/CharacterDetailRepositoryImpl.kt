package com.barquero.rickandmorty.data.repository

import com.barquero.rickandmorty.data.model.CharacterInfoApiModel
import com.barquero.rickandmorty.data.datasource.CharacterRemoteDataSource

class CharacterDetailRepositoryImpl(
    private val dataSource: CharacterRemoteDataSource
) : CharacterDetailRepository {

    override suspend fun requestCharacter(characterId: Int): Result<CharacterInfoApiModel> {
        return dataSource.characterDetail(characterId)
    }

}