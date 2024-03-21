package com.barquero.rickandmorty.data.api

import com.google.gson.annotations.SerializedName

data class CharacterApiModel(
    @SerializedName("info") val info: InfoApiModel? = null,
    @SerializedName("results") val results: List<CharacterInfoApiModel>? = null
)

data class InfoApiModel(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?
)

data class CharacterInfoApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("origin") val origin: OriginApiModel? = null,
    @SerializedName("location") val location: LocationApiModel? = null,
    @SerializedName("image") val image: String,
    @SerializedName("episode") val episode: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String
)

data class OriginApiModel(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class LocationApiModel(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)