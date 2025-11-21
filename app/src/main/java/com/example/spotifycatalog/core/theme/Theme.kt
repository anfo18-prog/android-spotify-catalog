package com.example.spotifycatalog.core.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
private val DarkColorScheme = darkColorScheme(
    primary = SpotifyGreen,
    onPrimary = White,
    background = SpotifyBlack,
    surface = SpotifyDarkGray,
    onBackground = White,
    onSurface = White,
    secondary = SpotifyMediumGray,
)
@Composable
fun SpotifyCatalogTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = SpotifyTypography,
        content = content
    )
}