package com.tracer.kiosk.presentation.feature.navigation.graph

import androidx.compose.ui.geometry.Offset

data class GraphNode(
    val id: String,
    var position: Offset,
    val neighbors: MutableList<String> = mutableListOf()
)