package com.example.spotifycatalog.presentation.artists

import com.example.spotifycatalog.domain.model.Artist

sealed interface ArtistsUiState {
    object Loading : ArtistsUiState
    data class Success(val artists: List<Artist>) : ArtistsUiState
    data class Error(val message: String) : ArtistsUiState
}