package com.example.spotifycatalog.data.mapper

import com.example.spotifycatalog.data.remote.dto.ArtistDto
import com.example.spotifycatalog.data.remote.dto.ExternalUrlDto
import com.example.spotifycatalog.data.remote.dto.FollowersDto
import com.example.spotifycatalog.domain.model.Artist

fun ArtistDto.toDomain(): Artist {
    val mainImage = images.maxByOrNull { it.height ?: 0 }?.url
    return Artist(
        id = id,
        name = name,
        popularity = popularity,
        genres = genres,
        imageUrl = mainImage,
        followers = followers.total
    )
}

fun Artist.toDto(): ArtistDto {
    return ArtistDto(
        id = id,
        name = name,
        popularity = popularity,
        genres = genres,
        images = emptyList(),
        followers = FollowersDto("", 0),
        href = "",
        uri = "",
        externalUrls = ExternalUrlDto("")
    )
}