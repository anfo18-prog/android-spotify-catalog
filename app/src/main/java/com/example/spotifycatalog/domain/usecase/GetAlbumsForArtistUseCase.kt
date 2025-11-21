package com.example.spotifycatalog.domain.usecase

import com.example.spotifycatalog.domain.repository.SpotifyRepository

class GetAlbumsForArtistUseCase(
    private val repository: SpotifyRepository
) {
    suspend operator fun invoke(artistId: String, limit: Int, offset: Int) =
        repository.getAlbumsForArtist(artistId, limit, offset)
}