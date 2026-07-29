package com.tracer.kiosk.presentation.feature.navigation.components.map

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import com.tracer.kiosk.presentation.feature.navigation.graph.MapCoordinate
import kotlin.math.roundToInt

@Composable
fun MapMarker(
    coordinate: MapCoordinate,
    editable: Boolean,
    mapWidth: Int,
    mapHeight: Int,
    onCoordinateChange: (MapCoordinate) -> Unit
) {

    val x = coordinate.x * mapWidth
    val y = coordinate.y * mapHeight

    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    x.roundToInt(),
                    y.roundToInt()
                )
            }
            .pointerInput(editable, mapWidth, mapHeight) {

                if (!editable) return@pointerInput

                detectDragGestures { change, dragAmount ->

                    change.consume()

                    val newX =
                        ((x + dragAmount.x) / mapWidth)
                            .coerceIn(0f, 1f)

                    val newY =
                        ((y + dragAmount.y) / mapHeight)
                            .coerceIn(0f, 1f)

                    onCoordinateChange(
                        MapCoordinate(
                            x = newX,
                            y = newY
                        )
                    )

                }

            }
    ) {

        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Marker",
            tint = Color.Red
        )

    }

}