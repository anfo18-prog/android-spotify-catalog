package com.example.spotifycatalog.data.mapper

import com.example.spotifycatalog.data.remote.dto.TrackDto
import com.example.spotifycatalog.domain.model.Track

fun TrackDto.toDomain(): Track {
    val artistName = artists.firstOrNull()?.name ?: ""
    return Track(
        id = id,
        name = name,
        durationMs = durationMs,
        previewUrl = null,
        artistName = artistName
    )
}