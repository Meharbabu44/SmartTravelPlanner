package com.meharbabu.smarttravelplanner.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFFFF5A5F),     // Coral Pink
    onPrimary = Color.White,
    secondary = Color(0xFFFFB400),   // Warm Yellow accent
    background = Color(0xFFF7F7F7),  // Light Gray
    surface = Color.White,
    onBackground = Color(0xFF222222), // Dark Gray text
    onSurface = Color(0xFF222222),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFFF5A5F),
    onPrimary = Color.Black,
    secondary = Color(0xFFFFB400),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onBackground = Color.White,
    onSurface = Color.White,
)

@Composable
fun ItineraryAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
