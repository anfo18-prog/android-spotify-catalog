package com.example.spotifycatalog.data.remote.datasource

import com.example.spotifycatalog.data.remote.api.SpotifyApi
import com.example.spotifycatalog.data.remote.dto.AlbumResponseDto
import com.example.spotifycatalog.data.remote.dto.ArtistDto
import com.example.spotifycatalog.data.remote.dto.ExternalUrlDto
import com.example.spotifycatalog.data.remote.dto.FollowersDto
import com.example.spotifycatalog.data.remote.dto.TracksResponseDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
@OptIn(ExperimentalCoroutinesApi::class)
class SpotifyRemoteDataSourceImplTest {
    private val api = mockk<SpotifyApi>()
    private val dataSource = SpotifyRemoteDataSourceImpl(api)

    @Test
    fun `getArtist calls API and returns result`() = runTest {
        val dto = ArtistDto(
            "123",
            "Daft Punk",
            0,
            emptyList(),
            images = emptyList(),
            followers = FollowersDto("", 0),
            href = "",
            uri = "",
            externalUrls = ExternalUrlDto(""))
        coEvery { api.getArtist("123") } returns dto
        val result = dataSource.getArtist("123")
        assertEquals("Daft Punk", result.name)
        assertEquals("123", result.id)
        coVerify { api.getArtist("123") }
    }

    @Test
    fun `getAlbumsByArtist calls API correctly`() = runTest {
        val response = AlbumResponseDto(emptyList())
        coEvery { api.getAlbumsForArtist("abc") } returns response
        val result = dataSource.getAlbumsForArtist("abc")
        assertEquals(0, result.items.size)
        coVerify { api.getAlbumsForArtist("abc") }
    }

    @Test
    fun `getTracksByAlbum calls API correctly`() = runTest {
        val response = TracksResponseDto(emptyList())
        coEvery { api.getTracksForAlbum("xyz") } returns response
        val result = dataSource.getTracksForAlbum("xyz")
        assertEquals(0, result.items.size)
        coVerify { api.getTracksForAlbum("xyz") }
    }
}