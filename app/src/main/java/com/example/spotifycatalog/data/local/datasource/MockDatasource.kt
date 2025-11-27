package com.example.spotifycatalog.data.local.datasource

interface MockDatasource {
    suspend fun getArtistsIds(): List<String>
}