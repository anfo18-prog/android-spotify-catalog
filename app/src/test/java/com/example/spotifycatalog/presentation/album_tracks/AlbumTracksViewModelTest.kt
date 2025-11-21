package com.example.spotifycatalog.presentation.album_tracks

import app.cash.turbine.test
import com.example.spotifycatalog.domain.model.Track
import com.example.spotifycatalog.domain.usecase.GetTracksForAlbumUseCase
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

class AlbumTracksViewModelTest {
    private lateinit var viewModel: AlbumTracksViewModel
    private val getTracksForAlbumUseCase = mockk<GetTracksForAlbumUseCase>()
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
        val albumId = "123"
        val tracks = listOf(
            Track(
                id = "id1",
                name = "Track 1",
                durationMs = 0,
                previewUrl = null,
                artistName = ""
            ),
            Track(
                id = "id2",
                name = "Track 2",
                durationMs = 0,
                previewUrl = null,
                artistName = ""
            ),
        )
        coEvery { getTracksForAlbumUseCase(albumId, limit = 20, offset = 0) } returns tracks
        viewModel = AlbumTracksViewModel(getTracksForAlbumUseCase)
        // Act & Assert
        viewModel.uiState.test {
            viewModel.loadTracks(albumId)
            // Initial state is Loading
            assertEquals(AlbumTracksUiState.Loading, awaitItem())
            // Final success state
            val success = awaitItem() as AlbumTracksUiState.Success
            assertEquals(2, success.tracks.size)
        }
    }
}