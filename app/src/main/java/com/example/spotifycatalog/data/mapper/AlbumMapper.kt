package com.example.spotifycatalog.data.mapper

import com.example.spotifycatalog.data.remote.dto.AlbumDto
import com.example.spotifycatalog.domain.model.Album

fun AlbumDto.toDomain(): Album {
    val mainImage = images.maxByOrNull { it.height ?: 0 }?.url
    return Album(
        id = id,
        name = name,
        imageUrl = mainImage,
        releaseDate = releaseDate,
        totalTracks = totalTracks
    )
}