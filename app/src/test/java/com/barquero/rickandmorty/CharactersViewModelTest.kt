package com.barquero.rickandmorty

import app.cash.turbine.test
import com.barquero.rickandmorty.data.model.CharacterApiModel
import com.barquero.rickandmorty.domain.usecase.GetCharactersUseCase
import com.barquero.rickandmorty.presentation.ui.charactersfeed.CharactersUiState
import com.barquero.rickandmorty.presentation.ui.charactersfeed.CharactersViewModel
import com.barquero.rickandmorty.presentation.util.NavigationState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: CharactersViewModel
    private val getCharactersUseCase: GetCharactersUseCase = mockk()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CharactersViewModel(getCharactersUseCase)
    }


    @Test
    fun `when getCharacterList is successful, emit SUCCESS state`() = runTest {
        val characters = CharacterApiModel()
        coEvery { getCharactersUseCase.execute() } returns flowOf(Result.success(characters))

        viewModel.getCharacterList()

        viewModel.characterFeedState.test {
            assertEquals(CharactersUiState.SUCCESS, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `when getCharacterList fails, emit FAILURE state`() = runTest {
        val errorMessage = "Error loading characters"
        coEvery { getCharactersUseCase.execute() } returns flowOf(
            Result.failure(
                RuntimeException(
                    errorMessage
                )
            )
        )

        viewModel.getCharacterList()

        viewModel.characterFeedState.test {
            val failureState = awaitItem() as CharactersUiState.FAILURE
            assertEquals(errorMessage, failureState.message)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `when onCharacterClicked, emit correct NavigationState`() = runTest {
        val characterId = 1

        // Recolectar de manera asíncrona para asegurar la captura del evento.
        val job = launch {
            viewModel.navigationState.collect { state ->
                if (state is NavigationState.CharacterDetail) {
                    assertEquals(characterId, state.characterId)
                } else {
                    fail("El estado de navegación no es el esperado.")
                }
            }
        }

        viewModel.onCharacterClicked(characterId)

        // Asegura que el flujo tenga tiempo para emitir.
        advanceUntilIdle()

        // Cancel after validation
        job.cancel()
    }
}
