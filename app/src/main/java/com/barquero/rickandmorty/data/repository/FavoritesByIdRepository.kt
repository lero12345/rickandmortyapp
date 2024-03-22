package com.barquero.rickandmorty.data.repository

interface FavoritesByIdRepository {
    suspend fun requestCharacterBy(characterId: Int): Result<Boolean>
}