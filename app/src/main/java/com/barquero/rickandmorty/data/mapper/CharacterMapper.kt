package com.barquero.rickandmorty.data.mapper

import com.barquero.rickandmorty.data.model.CharacterInfoApiModel
import com.barquero.rickandmorty.domain.model.Character
import com.barquero.rickandmorty.domain.model.Location
import com.barquero.rickandmorty.domain.model.Origin

class CharacterMapper {
    fun map(characterInfoApiModel: CharacterInfoApiModel): Character {
        return Character(
            id = characterInfoApiModel.id,
            name = characterInfoApiModel.name.orEmpty(),
            status = characterInfoApiModel.status.orEmpty(),
            species = characterInfoApiModel.species.orEmpty(),
            type = characterInfoApiModel.type.orEmpty(),
            gender = characterInfoApiModel.gender.orEmpty(),
            origin = Origin(
                name = characterInfoApiModel.origin?.name.orEmpty(),
                url = characterInfoApiModel.origin?.url.orEmpty()
            ),
            location = Location(
                name = characterInfoApiModel.location?.name.orEmpty(),
                url = characterInfoApiModel.location?.url.orEmpty()
            ),
            image = characterInfoApiModel.image.orEmpty(),
            episode = characterInfoApiModel.episode.orEmpty(),
            url = characterInfoApiModel.url.orEmpty(),
            created = characterInfoApiModel.created.orEmpty()
        )
    }
}
