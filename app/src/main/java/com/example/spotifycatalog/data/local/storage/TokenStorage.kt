package com.example.spotifycatalog.data.local.storage

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.spotifycatalog.core.constatnts.LocalStorageConstants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.tokenDataStore by preferencesDataStore(LocalStorageConstants.SPOTIFY_TOKEN_KEY)

class TokenStorage(private val context: Context) {

    companion object {
        private val KEY_TOKEN = stringPreferencesKey(LocalStorageConstants.ACCESS_TOKEN_KEY)
        private val KEY_EXPIRATION = longPreferencesKey(LocalStorageConstants.EXPIRATION_TIME_KEY)
    }

    suspend fun saveToken(token: String, expiresInSeconds: Int) {
        val expiration = System.currentTimeMillis() + (expiresInSeconds * 1000)

        context.tokenDataStore.edit { prefs ->
            prefs[KEY_TOKEN] = token
            prefs[KEY_EXPIRATION] = expiration
        }
    }

    suspend fun getToken(): String? =
        context.tokenDataStore.data.map { it[KEY_TOKEN] }.first()

    suspend fun getExpirationTime(): Long =
        context.tokenDataStore.data.map { it[KEY_EXPIRATION] ?: 0L }.first()

    suspend fun clear() {
        context.tokenDataStore.edit { it.clear() }
    }
}













