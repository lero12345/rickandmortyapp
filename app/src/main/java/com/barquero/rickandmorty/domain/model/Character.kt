package com.barquero.rickandmorty.domain.model

data class Character(
    val id: Int,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val origin: Origin = Origin(),
    val location: Location = Location(),
    val image: String = "",
    val episode: List<String> = listOf(),
    val url: String = "",
    val created: String = ""
)

data class Origin(
    val name: String = "",
    val url: String = ""
)

data class Location(
    val name: String = "",
    val url: String = ""
)