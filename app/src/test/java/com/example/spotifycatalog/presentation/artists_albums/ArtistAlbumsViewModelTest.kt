package com.example.spotifycatalog.presentation.artists_albums

import app.cash.turbine.test
import com.example.spotifycatalog.domain.model.Album
import com.example.spotifycatalog.domain.model.Artist
import com.example.spotifycatalog.domain.usecase.GetAlbumsForArtistUseCase
import com.example.spotifycatalog.domain.usecase.GetArtistUseCase
import com.example.spotifycatalog.presentation.artist_albums.ArtistAlbumsUiState
import com.example.spotifycatalog.presentation.artist_albums.ArtistAlbumsViewModel
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

class ArtistAlbumsViewModelTest {
    private lateinit var viewModel: ArtistAlbumsViewModel
    private val getAlbumsForArtistUseCase = mockk<GetAlbumsForArtistUseCase>()
    private val getArtistUseCase = mockk<GetArtistUseCase>()
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
    fun `loadArtist returns artists successfully`() = runTest {
        // Arrange
        val artistId = "123"
        val artist = Artist(
            id = "id1",
            name = "Artist 1",
            imageUrl = null,
            genres = emptyList(),
            followers = 0,
            popularity = 0
        )
        val albums = listOf(
            Album(
                id = "id1",
                name = "Album 1",
                imageUrl = null,
                releaseDate = "2025-01-01",
                totalTracks = 0
            ),
            Album(
                id = "id2",
                name = "Album 2",
                imageUrl = null,
                releaseDate = "2025-01-01",
                totalTracks = 0
            ),
        )
        coEvery { getArtistUseCase(artistId) } returns artist
        coEvery { getAlbumsForArtistUseCase(artistId, limit = 20, offset = 0) } returns albums
        viewModel = ArtistAlbumsViewModel(getArtistUseCase, getAlbumsForArtistUseCase)
        // Act & Assert
        viewModel.uiState.test {
            viewModel.loadArtist(artistId)
            // Initial state is Loading
            assertEquals(ArtistAlbumsUiState.Loading, awaitItem())
            // Final success state
            val success = awaitItem() as ArtistAlbumsUiState.Success
            assertEquals(2, success.albums.size)
        }
    }
}