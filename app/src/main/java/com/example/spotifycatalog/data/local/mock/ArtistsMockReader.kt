package com.example.spotifycatalog.data.local.mock

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import javax.inject.Inject

class ArtistsMockReader @Inject constructor(
    private val context: Context,
    private val moshi: Moshi
) {
    @OptIn(ExperimentalStdlibApi::class)
    fun loadArtistsIds(): List<String> {
        return try {
            val json = context.assets.open("artists.json").bufferedReader().use { it.readText() }
            val adapter = moshi.adapter<ArtistsMock>()
            val artistsMock = adapter.fromJson(json)

            artistsMock?.artistIds ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}