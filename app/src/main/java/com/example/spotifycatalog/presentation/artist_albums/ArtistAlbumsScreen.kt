package com.example.spotifycatalog.presentation.artist_albums

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.spotifycatalog.presentation.components.BackTopBar
import com.example.spotifycatalog.presentation.components.SpotifyCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistAlbumsScreen(
    id: String,
    viewModel: ArtistAlbumsViewModel = hiltViewModel(),
    onAlbumClick: (String) -> Unit,
    navController: NavController
) {
    val state by viewModel.uiState.collectAsState()
    var title by remember { mutableStateOf("") }

    LaunchedEffect(id) {
        viewModel.loadArtist(id)
    }

    Scaffold(
        topBar = {
            BackTopBar(
                title = title,
                onBackClick = { navController.popBackStack() }
            )
        }
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
                    title = data.artist.name
                    Column(
                        Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                    ) {
                        SpotifyCard(
                            title = data.artist.name,
                            imageUrl = data.artist.imageUrl,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {}
                        )
                        Spacer(Modifier.height(16.dp))
                        Text("Top albums", fontSize = 24.sp)
                        Spacer(Modifier.height(6.dp))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(data.albums) { album ->
                                SpotifyCard(
                                    title = "${album.name}, Tracks: ${album.totalTracks}",
                                    imageUrl = album.imageUrl,
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        onAlbumClick(album.id)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}