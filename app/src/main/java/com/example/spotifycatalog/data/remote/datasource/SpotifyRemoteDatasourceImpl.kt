package com.example.spotifycatalog.data.remote.datasource

import com.example.spotifycatalog.data.remote.api.SpotifyApi
import javax.inject.Inject

class SpotifyRemoteDataSourceImpl @Inject constructor(
    private val api: SpotifyApi
) : SpotifyRemoteDataSource {
    override suspend fun getSeveralArtists(ids: String) =
        api.getSeveralArtists(ids)

    override suspend fun getArtist(id: String) =
        api.getArtist(id)

    override suspend fun getAlbumsForArtist(id: String, limit: Int, offset: Int) =
        api.getAlbumsForArtist(id)

    override suspend fun getTracksForAlbum(id: String, limit: Int, offset: Int) =
        api.getTracksForAlbum(id)
}