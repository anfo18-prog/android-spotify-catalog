package com.example.spotifycatalog.data.local.datasource

import android.content.Context
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.spotifycatalog.data.local.mock.ArtistsMock
import dagger.hilt.android.qualifiers.ApplicationContext

class MockDatasourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val moshi: Moshi
) : MockDatasource {

    override suspend fun getArtistsIds(): List<String> = withContext(Dispatchers.IO) {
       val json = context.assets.open("artists.json")
           .bufferedReader().use { it.readText() }

       val adapter = moshi.adapter(ArtistsMock::class.java)
       adapter.fromJson(json)?.artistIds ?: emptyList()
   }
}