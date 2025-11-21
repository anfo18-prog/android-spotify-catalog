package com.example.spotifycatalog.presentation.artist_albums

import com.example.spotifycatalog.domain.model.Album
import com.example.spotifycatalog.domain.model.Artist

sealed interface ArtistAlbumsUiState {
    object Loading : ArtistAlbumsUiState
    data class Success(
        val artist: Artist,
        val albums: List<Album>
    ) : ArtistAlbumsUiState
    data class Error(val message: String) : ArtistAlbumsUiState
}