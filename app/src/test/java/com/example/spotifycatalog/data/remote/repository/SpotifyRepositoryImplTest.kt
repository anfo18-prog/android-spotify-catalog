package com.example.spotifycatalog.data.remote.repository

import com.example.spotifycatalog.data.mapper.toDto
import com.example.spotifycatalog.data.remote.datasource.SpotifyRemoteDataSource
import com.example.spotifycatalog.data.remote.dto.ArtistDto
import com.example.spotifycatalog.data.remote.dto.ArtistsResponseDto
import com.example.spotifycatalog.data.remote.dto.ExternalUrlDto
import com.example.spotifycatalog.data.remote.dto.FollowersDto
import com.example.spotifycatalog.data.repository.SpotifyRepositoryImpl
import com.example.spotifycatalog.domain.model.Artist
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SpotifyRepositoryImplTest {
    private val remoteDataSource = mockk<SpotifyRemoteDataSource>()
    private val repository = SpotifyRepositoryImpl(remoteDataSource)

    @Test
    fun `getArtists returns mapped artists`() = runTest {
        val ids = listOf("id1", "id2")
        val mockedArtists = listOf(
            Artist(id = "id1", name = "Artist 1", imageUrl = null, genres = emptyList(), followers = 0, popularity = 0),
            Artist(id = "id2", name = "Artist 2", imageUrl = null, genres = emptyList(), followers = 0, popularity = 0),
        )
        coEvery { remoteDataSource.getSeveralArtists("id1,id2") } returns ArtistsResponseDto(
            artists = mockedArtists.map { it.toDto() }
        )
        val result = repository.getArtists(ids)
        assertEquals(2, result.size)
        assertEquals("Artist 1", result[0].name)
        assertEquals("Artist 2", result[1].name)
    }

    @Test
    fun `getArtist returns mapped single artist`() = runTest {
        val dto = ArtistDto(
            "123",
            "Daft Punk",
            0,
            emptyList(),
            "",
            "",
            ExternalUrlDto(""),
            followers = FollowersDto("", 0),
            images = emptyList()
        )
        coEvery { remoteDataSource.getArtist("123") } returns dto
        val result = repository.getArtist("123")
        assertEquals("Daft Punk", result.name)
        assertEquals("123", result.id)
    }
}