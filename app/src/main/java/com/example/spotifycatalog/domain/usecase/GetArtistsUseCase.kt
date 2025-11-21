package com.example.spotifycatalog.domain.usecase

import com.example.spotifycatalog.domain.repository.SpotifyRepository

class GetArtistsUseCase(
    private val repository: SpotifyRepository
) {
    suspend operator fun invoke(ids: List<String>) =
        repository.getArtists(ids)
}