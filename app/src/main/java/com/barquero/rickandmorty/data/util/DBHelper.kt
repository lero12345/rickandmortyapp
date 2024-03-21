package com.barquero.rickandmorty.data.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import com.barquero.rickandmorty.data.api.CharacterInfoApiModel
import com.barquero.rickandmorty.data.api.LocationApiModel
import com.barquero.rickandmorty.data.api.OriginApiModel

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Favorites.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE favorites (" +
                    "id INTEGER PRIMARY KEY," +
                    "name TEXT," +
                    "status TEXT," +
                    "image TEXT" +
                    ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //
    }

    fun insertFavorite(character: CharacterInfoApiModel) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("id", character.id)
            put("name", character.name)
            put("status", character.status)
            put("image", character.image)
        }
        db.insert("favorites", null, values)
        db.close()
    }

    fun getAllFavorites(): List<CharacterInfoApiModel> {
        val favorites = mutableListOf<CharacterInfoApiModel>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM favorites", null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val name = getString(getColumnIndexOrThrow("name"))
                val status = getString(getColumnIndexOrThrow("status"))
                val image = getString(getColumnIndexOrThrow("image"))
                val character = CharacterInfoApiModel(
                    id,
                    name,
                    status,
                    "",
                    "",
                    "",
                    OriginApiModel("", ""),
                    LocationApiModel("", ""),
                    image,
                    emptyList(),
                    "",
                    ""
                )
                favorites.add(character)
            }
        }
        cursor.close()
        db.close()
        return favorites
    }
}
