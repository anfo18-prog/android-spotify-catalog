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
class GetArtistUseCaseTest {
    private val repository = mockk<SpotifyRepository>()
    private val useCase = GetArtistUseCase(repository)

    @Test
    fun `invoke returns artist from repository`() = runTest {
        val artist = Artist("123", "Porter Robinson", imageUrl = null, genres = emptyList(), followers = 0, popularity = 0)
        coEvery { repository.getArtist("123") } returns artist
        val result = useCase("123")
        assertEquals("Porter Robinson", result.name)
        assertEquals("123", result.id)
    }
}