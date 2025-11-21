package com.example.spotifycatalog.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.spotifycatalog.R
import androidx.compose.ui.unit.sp
val SpotifyFont = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold)
)
val SpotifyTypography = Typography(
    headlineMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = SpotifyFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    titleMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = SpotifyFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    bodyMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = SpotifyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelSmall = androidx.compose.ui.text.TextStyle(
        fontFamily = SpotifyFont,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )
)
