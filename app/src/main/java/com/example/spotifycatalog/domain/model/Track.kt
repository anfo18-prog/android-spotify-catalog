package com.example.spotifycatalog.domain.model

data class Track(
    val id: String,
    val name: String,
    val durationMs: Int,
    val previewUrl: String?,
    val artistName: String
)
