package com.barquero.rickandmorty.data.model

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
    @SerializedName("name") val name: String? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("species") val species: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("gender") val gender: String? = null,
    @SerializedName("origin") val origin: OriginApiModel? = null,
    @SerializedName("location") val location: LocationApiModel? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("episode") val episode: List<String>? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("created") val created: String? = null
)

data class OriginApiModel(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class LocationApiModel(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)