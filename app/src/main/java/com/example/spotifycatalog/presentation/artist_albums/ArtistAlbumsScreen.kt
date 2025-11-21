package com.example.spotifycatalog.presentation.artist_albums

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistAlbumsScreen(
    id: String,
    viewModel: ArtistAlbumsViewModel = hiltViewModel(),
    onAlbumClick: (String) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadArtist(id)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Artists") }) }
    ) {  padding ->
        Box (
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            when (state) {
                is ArtistAlbumsUiState.Loading ->
                    CircularProgressIndicator()
                is ArtistAlbumsUiState.Error ->
                    Text("Error: ${(state as ArtistAlbumsUiState.Error).message}")
                is ArtistAlbumsUiState.Success -> {
                    val data = state as ArtistAlbumsUiState.Success
                    Column(Modifier.padding(16.dp)) {
                        Text(data.artist.name, fontSize = 28.sp)
                        Spacer(Modifier.height(16.dp))
                        LazyColumn {
                            items(data.albums) { album ->
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .clickable { onAlbumClick(album.id) }
                                        .padding(8.dp)
                                ) {
                                    Text(album.name, fontSize = 20.sp)
                                    Text("Tracks: ${album.totalTracks}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}