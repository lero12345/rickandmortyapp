package com.barquero.rickandmorty.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey val characterId: Int,
    val name: String,
    val status: String,
    val image: String
)