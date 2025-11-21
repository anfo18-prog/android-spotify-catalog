package com.example.spotifycatalog.presentation.artists

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.spotifycatalog.presentation.components.SpotifyCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistsScreen(
    viewModel: ArtistsViewModel = hiltViewModel(),
    onArtistClick: (String) -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadArtists()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Top Spotify Artists", fontSize = 28.sp) }) }
    ) {  padding ->
        Box (
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            when (state) {
                ArtistsUiState.Loading ->
                    CircularProgressIndicator()
                is ArtistsUiState.Error ->
                    Text("Error: ${(state as ArtistsUiState.Error).message}")
                is ArtistsUiState.Success -> {
                    val artists = (state as ArtistsUiState.Success).artists
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .background(MaterialTheme.colorScheme.background),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(artists) { artist ->
                            SpotifyCard(
                                title = artist.name,
                                imageUrl = artist.imageUrl,
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    onArtistClick(artist.id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}