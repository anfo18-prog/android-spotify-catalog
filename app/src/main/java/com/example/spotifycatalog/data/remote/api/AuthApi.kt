package com.example.spotifycatalog.data.remote.api

import com.example.spotifycatalog.data.remote.dto.AuthResponseDto
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Field

interface AuthApi {
    @FormUrlEncoded
    @POST("api/token")
    suspend fun getToken(
        @Field("grant_type") grantType: String = "client_credentials",
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): AuthResponseDto
}