package com.example.spotifycatalog.presentation.artist_albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifycatalog.domain.usecase.GetAlbumsForArtistUseCase
import com.example.spotifycatalog.domain.usecase.GetArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistAlbumsViewModel @Inject constructor(
    private val getArtistUseCase: GetArtistUseCase,
    private val getAlbumsForArtistUseCase: GetAlbumsForArtistUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<ArtistAlbumsUiState>(ArtistAlbumsUiState.Loading)
    val uiState: StateFlow<ArtistAlbumsUiState> = _uiState
    fun loadArtist(id: String) {
        viewModelScope.launch {
            try {
                val artist = getArtistUseCase(id)
                val albums = getAlbumsForArtistUseCase(id, 20, 0)
                _uiState.value = ArtistAlbumsUiState.Success(artist, albums)
            } catch (e: Exception) {
                _uiState.value = ArtistAlbumsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
