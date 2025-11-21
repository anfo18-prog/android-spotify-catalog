package com.example.spotifycatalog.data.remote.manager

import com.example.spotifycatalog.data.local.storage.TokenStorage
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class TokenManager(
    private val storage: TokenStorage
) {

    private var cachedToken: String? = null
    private var cachedExpiration: Long = 0L

    suspend fun loadFromStorage() {
        cachedToken = storage.getToken()
        cachedExpiration = storage.getExpirationTime()
    }

    fun getCachedToken(): String? {
        val now = System.currentTimeMillis()
        return if (cachedToken != null && now < cachedExpiration) {
            cachedToken
        } else {
            null
        }
    }

    fun getLastExpiredToken(): String? = cachedToken

    suspend fun saveToken(token: String, expiration: Int) {
        cachedToken = token
        cachedExpiration = System.currentTimeMillis() + expiration * 1000
        storage.saveToken(token, expiration)
    }

    // To be executed in refresh token non run blocking
    fun saveTokenBlocking(token: String, expiration: Int) = runBlocking {
        saveToken(token, expiration)
    }
}