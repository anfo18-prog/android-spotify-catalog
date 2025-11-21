package com.example.spotifycatalog.data.remote.api

import com.example.spotifycatalog.data.remote.dto.AuthResponseDto
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApiSync {

    @FormUrlEncoded
    @POST("api/token")
    fun getTokenSync(
        @Field("grant_type") grantType: String = "client_credentials",
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Call<AuthResponseDto>

}