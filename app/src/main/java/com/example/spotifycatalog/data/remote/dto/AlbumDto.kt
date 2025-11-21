package com.example.spotifycatalog.data.remote.dto

import com.squareup.moshi.Json

data class AlbumDto(
    val id: String,
    val name: String,
    @param:Json(name = "album_type") val albumType: String,
    @param:Json(name = "total_tracks") val totalTracks: Int,
    val uri: String,
    val href: String,
    @param:Json(name = "release_date") val releaseDate: String,
    @param:Json(name = "images") val images: List<ImageDto>,
    val artists: List<SimpleArtistDto>
)

data class AlbumResponseDto(val items: List<AlbumDto>)
data class SimpleArtistDto(
    val id: String,
    val name: String,
    @param:Json(name = "external_urls") val externalUrls: ExternalUrlDto
)