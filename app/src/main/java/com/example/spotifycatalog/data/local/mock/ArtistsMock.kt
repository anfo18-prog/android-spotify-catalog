package com.example.spotifycatalog.data.local.mock

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistsMock(
    @param:Json(name = "artist_ids")
    val artistIds: List<String>
)