package com.example.spotifycatalog.domain.usecase

import com.example.spotifycatalog.domain.repository.SpotifyRepository

class GetTracksForAlbumUseCase(
    private val repository: SpotifyRepository
) {
    suspend operator fun invoke(albumId: String, limit: Int, offset: Int) =
        repository.getTracksForAlbum(albumId, limit, offset)
}