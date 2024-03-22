package com.barquero.rickandmorty.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorites")
    suspend fun getAll(): List<Favorite>

    @Query("SELECT * FROM favorites WHERE characterId = :characterId")
    suspend fun getFavoriteById(characterId: Int): Favorite?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(favoriteData: Favorite)

    @Query("DELETE FROM favorites WHERE characterId=:characterId")
    fun remove(characterId: Int)
}
