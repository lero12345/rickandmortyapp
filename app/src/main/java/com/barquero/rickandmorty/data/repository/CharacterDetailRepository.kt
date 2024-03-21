package com.barquero.rickandmorty.data.repository

import com.barquero.rickandmorty.data.api.CharacterInfoApiModel

interface CharacterDetailRepository {
    suspend fun requestCharacter(characterId: Int): Result<CharacterInfoApiModel>
}