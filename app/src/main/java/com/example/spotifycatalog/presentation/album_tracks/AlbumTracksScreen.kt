package com.example.spotifycatalog.presentation.album_tracks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.navigation.NavController
import com.example.spotifycatalog.presentation.components.BackTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumTracksScreen(
    albumId: String,
    viewModel: AlbumTracksViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(albumId) {
        viewModel.loadTracks(albumId)
    }

    Scaffold(
        topBar = {
            BackTopBar(
                title = "Tracks",
                onBackClick = { navController.popBackStack() }
            )
        }
    ) {  padding ->
        Box (
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center // TODO: Add an icon for app and splash, Add datasource

        ) {
            when (state) {
                AlbumTracksUiState.Loading ->
                    CircularProgressIndicator()
                is AlbumTracksUiState.Error ->
                    Text("Error: ${(state as AlbumTracksUiState.Error).message}")
                is AlbumTracksUiState.Success -> {
                    val tracks = (state as AlbumTracksUiState.Success).tracks
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(tracks) { track ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.MusicNote, contentDescription = null)
                                Column(Modifier.padding(12.dp)) {
                                    Text(track.name, fontSize = 20.sp)
                                    Text("Duration: ${track.durationMs / 1000}s")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}