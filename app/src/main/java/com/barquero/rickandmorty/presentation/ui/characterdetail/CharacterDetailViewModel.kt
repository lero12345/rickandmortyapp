package com.barquero.rickandmorty.presentation.ui.characterdetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barquero.rickandmorty.domain.usecase.AddCharacterFavoriteUseCase
import com.barquero.rickandmorty.domain.usecase.GetCharacterDetailUseCase
import com.barquero.rickandmorty.domain.usecase.GetFavoriteByIdUseCase
import com.barquero.rickandmorty.domain.usecase.GetFavoritesUseCase
import com.barquero.rickandmorty.domain.usecase.RemoveFavoriteUseCase
import com.barquero.rickandmorty.presentation.navigation.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addCharacterFavoriteUseCase: AddCharacterFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val getFavoriteByIdUseCase: GetFavoriteByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState: MutableStateFlow<CharacterDetailUiState> = MutableStateFlow(
        CharacterDetailUiState()
    )
    val uiState = _uiState.asStateFlow()

    private var characterId: Int = 0

    fun onFavoriteClicked() {
        viewModelScope.launch {
            checkFavoriteStatus(characterId).onSuccess { isFavorite ->
                if (isFavorite) removeFavoriteUseCase.removeFavorite(characterId)
                else addCharacterFavoriteUseCase.execute(characterId)
                _uiState.update { it.copy(isFavorite = !isFavorite) }
            }
        }
    }

    init {
        characterId = savedStateHandle[Page.CharacterDetail.CHARACTER_ID] ?: 0
        viewModelScope.launch {
            var isFavorite = false
            async { checkFavoriteStatus(characterId) }.await().onSuccess {
                isFavorite = it
            }.onFailure { isFavorite = false }

            getCharacterDetailUseCase.execute(characterId).onSuccess {
                _uiState.value = CharacterDetailUiState(
                    it.id,
                    it.name.orEmpty(),
                    it.status.orEmpty(),
                    it.image.orEmpty(),
                    isFavorite = isFavorite
                )
            }.onFailure { Log.d("error", "failed") }
        }
    }

    private suspend fun checkFavoriteStatus(characterId: Int): Result<Boolean> =
        getFavoriteByIdUseCase.execute(characterId)

}