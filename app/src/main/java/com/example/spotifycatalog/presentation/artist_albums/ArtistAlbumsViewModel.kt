package com.example.spotifycatalog.presentation.artist_albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifycatalog.domain.model.Album
import com.example.spotifycatalog.domain.model.Artist
import com.example.spotifycatalog.domain.usecase.GetAlbumsForArtistUseCase
import com.example.spotifycatalog.domain.usecase.GetArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistAlbumsViewModel @Inject constructor(
    private val getArtistUseCase: GetArtistUseCase,
    private val getAlbumsForArtistUseCase: GetAlbumsForArtistUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<ArtistAlbumsUiState>(ArtistAlbumsUiState.Loading)
    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    private lateinit var artist: Artist
    val uiState: StateFlow<ArtistAlbumsUiState> = _uiState
    val albums = _albums.asStateFlow()



    fun loadArtist(id: String) {
        viewModelScope.launch {
            try {
                artist = getArtistUseCase(id)
                // val albums = getAlbumsForArtistUseCase(artist.id)
                // _uiState.value = ArtistAlbumsUiState.Success(artist, albums)
                _uiState.value = ArtistAlbumsUiState.Success(artist, albums.value)
                getAlbumsByTime(artist.id, 10000)
            } catch (e: Exception) {
                _uiState.value = ArtistAlbumsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun getAlbumsByTime(id: String, timeDelay: Long, limit: Int = 3) {
        viewModelScope.launch {
            var offset = 0
            do {
                val resp = getAlbumsForArtistUseCase(id, limit, offset)
                if (resp.isEmpty()) break

                _albums.value = _albums.value + resp
                _uiState.value = ArtistAlbumsUiState.Success(artist, _albums.value)
                offset++
                delay(timeDelay)
            }
            while (true)
        }
    }
}
