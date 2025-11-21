package com.example.spotifycatalog.data.remote.dto

import com.squareup.moshi.Json

data class TrackDto(
    val id: String,
    val name: String,
    val uri: String,
    val href: String,
    @param:Json(name = "duration_ms") val durationMs: Int,
    @param:Json(name = "track_number") val trackNumber: Int,
    val explicit: Boolean,
    val artists: List<SimpleArtistDto>,
    @param:Json(name = "external_urls") val externalUrls: ExternalUrlDto
)

data class TracksResponseDto(val items: List<TrackDto>)