package com.example.spotifycatalog.domain.model

data class Artist(
    val id: String,
    val name: String,
    val popularity: Int,
    val genres: List<String>,
    val imageUrl: String?,
    val followers: Int?
)