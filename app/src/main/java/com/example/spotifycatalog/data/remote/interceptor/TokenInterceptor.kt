package com.example.spotifycatalog.data.remote.interceptor

import com.example.spotifycatalog.core.constatnts.HttpConstants
import com.example.spotifycatalog.data.remote.api.AuthApiSync
import com.example.spotifycatalog.data.remote.manager.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val tokenManager: TokenManager,
    private val authApiSync: AuthApiSync,
    private val clientId: String,
    private val clientSecret: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var token = tokenManager.getCachedToken()
        // Ensures token exists
        if (token == null) {
            val tokenResponse = authApiSync.getTokenSync(
                clientId = clientId,
                clientSecret = clientSecret
            ).execute()

            if (tokenResponse.isSuccessful) {
                val body = tokenResponse.body()
                if (body != null) {
                    tokenManager.saveTokenBlocking(body.accessToken, body.expiresIn)
                    token = body.accessToken
                }
            }
        }
        val request = chain.request().newBuilder()
            .addHeader(
                HttpConstants.HEADER_AUTHORIZATION,
                "${HttpConstants.TOKEN_TYPE_BEARER} ${token ?: ""}")
            .build()

        return chain.proceed(request)
    }
}