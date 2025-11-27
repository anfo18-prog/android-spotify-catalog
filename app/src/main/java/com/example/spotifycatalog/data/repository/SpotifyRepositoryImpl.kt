package com.example.spotifycatalog.data.repository

import com.example.spotifycatalog.data.local.datasource.MockDatasource
import com.example.spotifycatalog.data.mapper.toDomain
import com.example.spotifycatalog.data.remote.api.SpotifyApi
import com.example.spotifycatalog.data.remote.datasource.SpotifyRemoteDataSource
import com.example.spotifycatalog.domain.model.Artist
import com.example.spotifycatalog.domain.model.Album
import com.example.spotifycatalog.domain.model.Track
import com.example.spotifycatalog.domain.repository.SpotifyRepository
import javax.inject.Inject

class SpotifyRepositoryImpl @Inject constructor(
    private val remoteDataSource: SpotifyRemoteDataSource,
    private val mockDatasource: MockDatasource
) : SpotifyRepository {
    override suspend fun getArtists(ids: List<String>): List<Artist> {
        val artistsResponse = remoteDataSource.getSeveralArtists(ids.joinToString(","))
        return artistsResponse.artists.map { it.toDomain() }
    }

    override suspend fun getArtist(id: String): Artist =
        remoteDataSource.getArtist(id).toDomain()

    override suspend fun getAlbumsForArtist(artistId: String, limit: Int, offset: Int): List<Album> {
        val albumsResponse = remoteDataSource.getAlbumsForArtist(artistId, limit, offset)
        return albumsResponse.items.map { it.toDomain() }
    }

    override suspend fun getTracksForAlbum(albumId: String, limit: Int, offset: Int): List<Track> {
        val tracksResponse = remoteDataSource.getTracksForAlbum(albumId, limit, offset)
        return tracksResponse.items.map { it.toDomain() }
    }

    override suspend fun getArtistIds(): List<String> =
        mockDatasource.getArtistsIds()
}