package com.example.spotifycatalog.domain.usecase

import com.example.spotifycatalog.domain.repository.SpotifyRepository

class GetArtistUseCase(
    private val repository: SpotifyRepository
) {
    suspend operator fun invoke(id: String) =
        repository.getArtist(id)
}