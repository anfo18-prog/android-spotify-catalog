package com.example.spotifycatalog.data.remote.datasource

import com.example.spotifycatalog.data.remote.dto.AlbumResponseDto
import com.example.spotifycatalog.data.remote.dto.ArtistDto
import com.example.spotifycatalog.data.remote.dto.ArtistsResponseDto
import com.example.spotifycatalog.data.remote.dto.TracksResponseDto

interface SpotifyRemoteDataSource {
    suspend fun getSeveralArtists(ids: String): ArtistsResponseDto
    suspend fun getArtist(id: String): ArtistDto
    suspend fun getAlbumsForArtist(id: String, limit: Int = 20, offset: Int = 0): AlbumResponseDto
    suspend fun getTracksForAlbum(id: String, limit: Int = 20, offset: Int = 0): TracksResponseDto
}
