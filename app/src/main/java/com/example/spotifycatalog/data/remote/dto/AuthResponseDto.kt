package com.example.spotifycatalog.data.remote.dto

import com.squareup.moshi.Json

data class AuthResponseDto(
    @param:Json(name = "access_token") val accessToken: String,
    @param:Json(name = "token_type") val tokenType: String,
    @param:Json(name = "expires_in") val expiresIn: Int
)