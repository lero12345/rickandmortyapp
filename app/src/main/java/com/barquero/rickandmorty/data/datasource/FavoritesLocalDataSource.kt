package com.barquero.rickandmorty.data.datasource

import com.barquero.rickandmorty.data.model.CharacterInfoApiModel
import com.barquero.rickandmorty.data.database.Favorite
import com.barquero.rickandmorty.data.database.FavoritesDao
import com.barquero.rickandmorty.data.util.DiskExecutor
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class FavoritesLocalDataSource(
    private val favoritesDao: FavoritesDao,
    private val executor: DiskExecutor
) {
    suspend fun requestFavorites(): Flow<Result<List<CharacterInfoApiModel>>> =
        withContext(executor.asCoroutineDispatcher()) {
            return@withContext flow {
                val characters = favoritesDao.getAll().map {
                    CharacterInfoApiModel(
                        id = it.characterId,
                        name = it.name,
                        image = it.image,
                        status = it.status
                    )
                }
                if (characters.isNotEmpty()) {
                    emit(Result.success(characters))
                } else {
                    emit(Result.failure(Exception("No characters in local db")))
                }
            }
        }

    suspend fun checkCharacterFavoriteStatus(characterId: Int): Result<Boolean> =
        withContext(executor.asCoroutineDispatcher()) {

            return@withContext Result.success(favoritesDao.getFavoriteById(characterId) != null)
        }


//    suspend fun requestCharacterSavedById(characterId: Int): Result<CharacterInfoApiModel> {
//        val character = favoritesDao.getFavoriteById(characterId)
//        return if (character != null) {
//            Result.success(
//                CharacterInfoApiModel(
//                    id = character.characterId,
//                    name = character.name,
//                    image = character.image,
//                    status = character.status
//                )
//            )
//        } else {
//            Result.failure(Exception("No characters in local db"))
//        }
//    }

    suspend fun addFavoriteCharacter(characterData: CharacterInfoApiModel) =
        withContext(executor.asCoroutineDispatcher()) {
            favoritesDao.add(
                Favorite(
                    characterId = characterData.id,
                    name = characterData.name.orEmpty(),
                    image = characterData.image.orEmpty(),
                    status = characterData.status.orEmpty()
                )
            )
        }

    suspend fun removeFavorite(characterId: Int) = withContext(executor.asCoroutineDispatcher()) {
        favoritesDao.remove(characterId)
    }
}