package com.barquero.rickandmorty.data.datasource

import com.barquero.rickandmorty.data.api.CharacterApiModel
import com.barquero.rickandmorty.data.api.RickAndMortyService

class CharacterRemoteDataSource(
    private val rickAndMortyService: RickAndMortyService
) {
    suspend operator fun invoke(): Result<CharacterApiModel> =
        rickAndMortyService.getCharacters()
}