package com.barquero.rickandmorty.data.repository

import com.barquero.rickandmorty.data.model.CharacterApiModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun requestCharacters(): Flow<Result<CharacterApiModel>>
}
