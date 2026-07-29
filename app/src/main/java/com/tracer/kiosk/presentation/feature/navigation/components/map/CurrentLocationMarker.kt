package com.tracer.kiosk.presentation.feature.navigation.components.map

import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

@Composable
fun CurrentLocationMarker(
    position: Offset
) {

    Icon(
        imageVector = Icons.Default.LocationOn,
        contentDescription = "Current Location",
        tint = Color.Red,
        modifier = Modifier.offset {
            IntOffset(
                position.x.roundToInt(),
                position.y.roundToInt()
            )
        }
    )

}