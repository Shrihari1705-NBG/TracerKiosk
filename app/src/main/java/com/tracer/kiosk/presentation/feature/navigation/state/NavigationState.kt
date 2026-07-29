package com.tracer.kiosk.presentation.feature.navigation.state

import com.tracer.kiosk.presentation.feature.navigation.graph.MapCoordinate
import com.tracer.kiosk.presentation.feature.navigation.model.Destination
import com.tracer.kiosk.presentation.feature.navigation.model.DestinationCategory

data class NavigationState(

    val query: String = "",

    val selectedCategory: DestinationCategory? = null,

    val selectedDestination: Destination? = null,

    // Current location on the floor map
    val currentLocation: MapCoordinate = MapCoordinate(
        x = 0.54f,
        y = 0.20f
    ),

    // Enables dragging during development
    val editMode: Boolean = true

)