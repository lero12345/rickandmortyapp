package com.barquero.rickandmorty.data.api

import com.barquero.rickandmorty.data.model.CharacterApiModel
import com.barquero.rickandmorty.data.model.CharacterInfoApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {
    @GET("/api/character/{id}")
    suspend fun getCharacter(@Path("id") characterId: Int): Result<CharacterInfoApiModel>

    @GET("/api/character")
    suspend fun getCharacters(): Result<CharacterApiModel>
}