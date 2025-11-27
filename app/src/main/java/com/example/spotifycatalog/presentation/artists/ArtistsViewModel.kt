package com.example.spotifycatalog.presentation.artists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifycatalog.data.local.mock.ArtistsMockReader
import com.example.spotifycatalog.domain.usecase.GetArtistUseCase
import com.example.spotifycatalog.domain.usecase.GetArtistsIdsUseCase
import com.example.spotifycatalog.domain.usecase.GetArtistsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val getArtistIdsUseCase: GetArtistsIdsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<ArtistsUiState>(ArtistsUiState.Loading)
    val uiState: StateFlow<ArtistsUiState> = _uiState

    fun loadArtists() {
        viewModelScope.launch {
            try {
                val ids = getArtistIdsUseCase()
                val result = getArtistsUseCase(ids)
                _uiState.value = ArtistsUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = ArtistsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
