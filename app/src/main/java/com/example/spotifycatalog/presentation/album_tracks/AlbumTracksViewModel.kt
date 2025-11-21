package com.example.spotifycatalog.presentation.album_tracks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifycatalog.domain.usecase.GetTracksForAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumTracksViewModel @Inject constructor(
    private val getTracksForAlbumUseCase: GetTracksForAlbumUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<AlbumTracksUiState>(AlbumTracksUiState.Loading)
    val uiState: StateFlow<AlbumTracksUiState> = _uiState
    fun loadTracks(albumId: String) {
        viewModelScope.launch {
            try {
                val tracks = getTracksForAlbumUseCase(albumId, 20, 0)
                _uiState.value = AlbumTracksUiState.Success(tracks)
            } catch (e: Exception) {
                _uiState.value = AlbumTracksUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}