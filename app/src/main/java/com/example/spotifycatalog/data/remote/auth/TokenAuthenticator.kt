package com.example.spotifycatalog.data.remote.auth

import com.example.spotifycatalog.core.constatnts.HttpConstants
import com.example.spotifycatalog.data.remote.api.AuthApiSync
import com.example.spotifycatalog.data.remote.manager.TokenManager
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val tokenManager: TokenManager,
    private val authApiSync: AuthApiSync,
    private val clientId: String,
    private val clientSecret: String
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (responseCount(response) >= 2) {
            return null
        }

        synchronized(this) {
            // Checks if token was already refreshed
            val currentToken = tokenManager.getCachedToken()
            if (currentToken != null && currentToken != tokenManager.getLastExpiredToken()) {
                return response.request.newBuilder()
                    .removeHeader(HttpConstants.HEADER_AUTHORIZATION)
                    .addHeader(
                        HttpConstants.HEADER_AUTHORIZATION,
                        "${HttpConstants.TOKEN_TYPE_BEARER} $currentToken"
                        )
                    .build()
            }

            // Force asynchronous refresh
            val tokenResponse = authApiSync.getTokenSync(
                clientId = clientId,
                clientSecret = clientSecret
            ).execute()

            if (!tokenResponse.isSuccessful) return null
            val body = tokenResponse.body() ?: return null

            tokenManager.saveTokenBlocking(body.accessToken, body.expiresIn)
            val newToken = body.accessToken

            return response.request.newBuilder()
                .removeHeader(HttpConstants.HEADER_AUTHORIZATION)
                .addHeader(HttpConstants.HEADER_AUTHORIZATION, "${HttpConstants.TOKEN_TYPE_BEARER} $newToken")
                .build()
        }
    }

    private fun responseCount(response: Response): Int {
        var result = 1
        var prior = response.priorResponse
        while (response.priorResponse != null) {
            result++
            prior = prior?.priorResponse
        }
        return result
    }

}