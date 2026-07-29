package com.tracer.kiosk.presentation.feature.navigation.components.map

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

@Composable
fun CurrentLocationMarker(
    position: Offset,
    onPositionChanged: (Offset) -> Unit
) {

    // Local position used while dragging
    var currentPosition by remember {
        mutableStateOf(position)
    }

    // Keep local position in sync with parent state
    LaunchedEffect(position) {
        currentPosition = position
    }

    Icon(
        imageVector = Icons.Default.LocationOn,
        contentDescription = "Current Location",
        tint = Color.Red,
        modifier = Modifier
            .offset {
                IntOffset(
                    currentPosition.x.roundToInt(),
                    currentPosition.y.roundToInt()
                )
            }
            .pointerInput(Unit) {

                detectDragGestures { change, dragAmount ->

                    change.consume()

                    currentPosition = Offset(
                        currentPosition.x + dragAmount.x,
                        currentPosition.y + dragAmount.y
                    )

                    onPositionChanged(currentPosition)

                }

            }
    )
}