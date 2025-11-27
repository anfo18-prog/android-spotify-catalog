package com.example.spotifycatalog.domain.usecase

import com.example.spotifycatalog.domain.repository.SpotifyRepository
import javax.inject.Inject

class GetArtistsIdsUseCase @Inject constructor(
    private val repository: SpotifyRepository
) {
    suspend operator fun invoke(): List<String> =
        repository.getArtistIds()
}