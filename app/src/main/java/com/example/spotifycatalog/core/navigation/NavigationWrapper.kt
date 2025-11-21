package com.example.spotifycatalog.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spotifycatalog.presentation.album_tracks.AlbumTracksScreen
import com.example.spotifycatalog.presentation.artist_albums.ArtistAlbumsScreen
import com.example.spotifycatalog.presentation.artists.ArtistsScreen

@Composable
fun NavigationWrapper(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = "artists") {
        composable("artists") {
            ArtistsScreen(
                onArtistClick = { artistId ->
                    navController.navigate("artist/$artistId")
                }
            )
        }
        composable("artist/{id}") { backStack ->
            val id = backStack.arguments?.getString("id") ?: return@composable
            ArtistAlbumsScreen(
                id = id,
                onAlbumClick = { albumId ->
                    navController.navigate("album/$albumId")
                }
            )
        }
        composable("album/{albumId}") { backStack ->
            val albumId = backStack.arguments?.getString("albumId") ?: return@composable
            AlbumTracksScreen(albumId = albumId)
        }
    }
}