package com.barquero.rickandmorty.presentation.ui.charactersfeed

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barquero.rickandmorty.data.api.CharacterApiModel
import com.barquero.rickandmorty.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val useCase: GetCharactersUseCase
) : ViewModel() {

    var charactersListData: CharacterApiModel by mutableStateOf(CharacterApiModel())
    val characterFeedState = MutableStateFlow<CharactersUiState>(CharactersUiState.START)

    fun getCharacterList() {
        viewModelScope.launch {
            useCase.execute().collect { result ->
                result.onSuccess {
                    //emit to state
                    charactersListData = result.getOrDefault(CharacterApiModel())
                    characterFeedState.emit(CharactersUiState.SUCCESS)

                }.onFailure {
                    characterFeedState.emit(CharactersUiState.FAILURE(it.message.orEmpty()))
                }
            }
        }
    }
}