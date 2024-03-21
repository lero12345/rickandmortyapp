package com.barquero.rickandmorty.presentation.ui.characterdetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barquero.rickandmorty.domain.usecase.GetCharacterDetailUseCase
import com.barquero.rickandmorty.presentation.navigation.Page
import com.barquero.rickandmorty.presentation.util.NavigationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState: MutableStateFlow<CharacterDetailUiState> = MutableStateFlow(
        CharacterDetailUiState()
    )
    val uiState = _uiState.asStateFlow()

    private var characterId: Int = 0

    init {
        characterId = savedStateHandle[Page.CharacterDetail.CHARACTER_ID] ?: 0
        viewModelScope.launch {
            getCharacterDetailUseCase.execute(characterId).onSuccess {
                _uiState.value = CharacterDetailUiState(
                    it.id,
                    it.name,
                    it.status,
                    it.image
                )
            }.onFailure { Log.d("error", "failed") }
        }
    }
}