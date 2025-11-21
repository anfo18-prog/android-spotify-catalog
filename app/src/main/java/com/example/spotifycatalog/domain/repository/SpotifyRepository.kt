package com.example.spotifycatalog.domain.repository

import com.example.spotifycatalog.domain.model.Album
import com.example.spotifycatalog.domain.model.Artist
import com.example.spotifycatalog.domain.model.Track

interface SpotifyRepository {
    suspend fun getArtists(ids: List<String>): List<Artist>
    suspend fun getArtist(id: String): Artist
    suspend fun getAlbumsForArtist(
        artistId: String,
        limit: Int = 20,
        offset: Int = 0
    ): List<Album>
    suspend fun getTracksForAlbum(
        albumId: String,
        limit: Int = 20,
        offset: Int = 0
    ): List<Track>
}