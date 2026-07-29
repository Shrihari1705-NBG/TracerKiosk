package com.tracer.kiosk.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(

    primary = TracerPrimary,
    secondary = TracerSecondary,

    background = ScreenBackground,
    surface = TracerSurface,

    onPrimary = TracerOnPrimary,
    onSecondary = TracerOnSecondary,
    onBackground = TracerOnBackground,
    onSurface = TracerOnSurface,

    outline = TracerOutline,

    error = TracerError
)

private val DarkColorScheme = darkColorScheme(

    primary = TracerPrimary,
    secondary = TracerSecondary,

    background = TracerPrimary,
    surface = TracerPrimary,

    onPrimary = TracerOnPrimary,
    onSecondary = TracerOnSecondary,
    onBackground = TracerOnPrimary,
    onSurface = TracerOnPrimary,

    outline = TracerOutline,

    error = TracerError
)

@Composable
fun TracerKioskTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = TracerTypography,
        shapes = TracerShapes,
        content = content
    )
}