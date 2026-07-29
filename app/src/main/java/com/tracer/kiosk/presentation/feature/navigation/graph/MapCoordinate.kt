package com.tracer.kiosk.presentation.feature.navigation.graph

/**
 * Represents a point on the map using normalized coordinates.
 *
 * x = 0.0 -> Left edge
 * x = 1.0 -> Right edge
 *
 * y = 0.0 -> Top edge
 * y = 1.0 -> Bottom edge
 */
data class MapCoordinate(
    val x: Float,
    val y: Float
)