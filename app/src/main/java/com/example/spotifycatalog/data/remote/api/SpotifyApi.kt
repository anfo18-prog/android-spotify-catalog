package com.example.spotifycatalog.data.remote.api

import com.example.spotifycatalog.data.remote.dto.AlbumResponseDto
import com.example.spotifycatalog.data.remote.dto.ArtistDto
import com.example.spotifycatalog.data.remote.dto.ArtistsResponseDto
import com.example.spotifycatalog.data.remote.dto.TracksResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyApi {
    @GET("v1/artists")
    suspend fun getSeveralArtists(
        @Query("ids") ids: String
    ): ArtistsResponseDto

    @GET("v1/artists/{id}")
    suspend fun getArtist(
        @Path("id") artistId: String
    ): ArtistDto

    @GET("v1/artists/{id}/albums")
    suspend fun getAlbumsForArtist(
        @Path("id") artistId: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): AlbumResponseDto

    @GET("v1/albums/{id}/tracks")
    suspend fun getTracksForAlbum(
        @Path("id") albumId: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): TracksResponseDto
}