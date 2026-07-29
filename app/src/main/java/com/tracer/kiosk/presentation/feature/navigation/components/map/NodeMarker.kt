package com.tracer.kiosk.presentation.feature.navigation.components.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun NodeMarker(
    position: Offset,
    isSelected: Boolean
) {
    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    position.x.roundToInt(),
                    position.y.roundToInt()
                )
            }
            .size(14.dp)
            .background(
                color = if (isSelected) Color.Red else Color.Blue,
                shape = CircleShape
            )
    )
}