package com.example.spotifycatalog.data.remote.dto

import com.squareup.moshi.Json

data class ArtistDto(
    val id: String,
    val name: String,
    val popularity: Int,
    val genres: List<String>,
    val href: String,
    val uri: String,
    @param:Json(name = "external_urls") val externalUrls: ExternalUrlDto,
    val followers: FollowersDto,
    val images: List<ImageDto>
)
data class ArtistsResponseDto(val artists: List<ArtistDto>)
data class ExternalUrlDto(val spotify: String?)
data class FollowersDto(val href: String?, val total: Int?)
data class ImageDto(val url: String?, val height: Int?, val width: Int?)