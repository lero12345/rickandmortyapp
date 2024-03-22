package com.barquero.rickandmorty.presentation.ui.characterdetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barquero.rickandmorty.domain.usecase.GetCharacterDetailUseCase
import com.barquero.rickandmorty.domain.usecase.GetFavoriteByIdUseCase
import com.barquero.rickandmorty.domain.usecase.GetFavoritesUseCase
import com.barquero.rickandmorty.presentation.navigation.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
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
                Log.d("cheack",  "asdasd")
            }
        }
    }

    init {
        characterId = savedStateHandle[Page.CharacterDetail.CHARACTER_ID] ?: 0
        viewModelScope.launch {
            getCharacterDetailUseCase.execute(characterId).onSuccess {
                _uiState.value = CharacterDetailUiState(
                    it.id,
                    it.name.orEmpty(),
                    it.status.orEmpty(),
                    it.image.orEmpty()
                )
            }.onFailure { Log.d("error", "failed") }
        }
    }

    private suspend fun checkFavoriteStatus(characterId: Int): Result<Boolean> =
        getFavoriteByIdUseCase.execute(characterId)

}