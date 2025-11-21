package com.example.spotifycatalog.presentation.artists

import app.cash.turbine.test
import com.example.spotifycatalog.data.local.mock.ArtistsMockReader
import com.example.spotifycatalog.domain.model.Artist
import com.example.spotifycatalog.domain.usecase.GetArtistsUseCase
import com.example.spotifycatalog.domain.usecase.GetAlbumsForArtistUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)

class ArtistsViewModelTest {
    private lateinit var viewModel: ArtistsViewModel
    private val getArtistsUseCase = mockk<GetArtistsUseCase>()
    private val getArtistDetailsUseCase = mockk<GetAlbumsForArtistUseCase>()
    private val mockReader = mockk<ArtistsMockReader>()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadArtists returns list successfully`() = runTest {
        // Arrange
        val ids = listOf("id1", "id2")
        val artistList = listOf(
            Artist(id = "id1", name = "Artist 1", imageUrl = null, genres = emptyList(), followers = 0, popularity = 0),
            Artist(id = "id2", name = "Artist 2", imageUrl = null, genres = emptyList(), followers = 0, popularity = 0),
        )
        coEvery { mockReader.loadArtistsIds() } returns ids
        coEvery { getArtistsUseCase(ids) } returns artistList
        viewModel = ArtistsViewModel(getArtistsUseCase, mockReader)
        // Act & Assert
        viewModel.uiState.test {
            viewModel.loadArtists()
            // Initial state is Loading
            assertEquals(ArtistsUiState.Loading, awaitItem())
            // Final success state
            val success = awaitItem() as ArtistsUiState.Success
            assertEquals(2, success.artists.size)
        }
    }

    @Test
    fun `loadArtists emits Error when use case throws`() = runTest {
        val ids = listOf("id1", "id2")
        val errorMessage = "Network failure"
        coEvery { mockReader.loadArtistsIds() } returns ids
        coEvery { getArtistsUseCase(ids) } throws RuntimeException(errorMessage)
        viewModel = ArtistsViewModel(getArtistsUseCase, mockReader)
        viewModel.uiState.test {
            viewModel.loadArtists()
            assertEquals(ArtistsUiState.Loading, awaitItem())
            val error = awaitItem() as ArtistsUiState.Error
            assertEquals(errorMessage, error.message)
        }
    }
}