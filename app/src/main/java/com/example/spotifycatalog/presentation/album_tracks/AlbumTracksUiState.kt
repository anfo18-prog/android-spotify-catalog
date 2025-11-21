package com.example.spotifycatalog.presentation.album_tracks

import com.example.spotifycatalog.domain.model.Track

sealed interface AlbumTracksUiState {
    object Loading : AlbumTracksUiState
    data class Success(val tracks: List<Track>) : AlbumTracksUiState
    data class Error(val message: String) : AlbumTracksUiState
}