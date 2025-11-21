package com.example.spotifycatalog.domain.usecase

import com.example.spotifycatalog.domain.model.Artist
import com.example.spotifycatalog.domain.repository.SpotifyRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetArtistsUseCaseTest {
    private val repository = mockk<SpotifyRepository>()
    private val useCase = GetArtistsUseCase(repository)

    @Test
    fun `invoke returns artists from repository`() = runTest {
        val ids = listOf("1", "2")
        val artists = listOf(
            Artist(id = "id1", name = "Artist 1", imageUrl = null, genres = emptyList(), followers = 0, popularity = 0),
            Artist(id = "id2", name = "Artist 2", imageUrl = null, genres = emptyList(), followers = 0, popularity = 0),
        )
        coEvery { repository.getArtists(ids) } returns artists
        val result = useCase(ids)
        assertEquals(2, result.size)
        assertEquals("Artist 1", result[0].name)
    }
}