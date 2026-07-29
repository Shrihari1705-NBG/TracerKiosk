package com.tracer.kiosk.presentation.feature.navigation.algorithm

import com.tracer.kiosk.presentation.feature.navigation.graph.GraphNode

object RouteGenerator {

    private var currentRoute: List<GraphNode> = emptyList()

    fun generateRoute(
        startNodeId: String,
        destinationNodeId: String
    ) {
        currentRoute = PathFinder.findPath(
            startNodeId = startNodeId,
            destinationNodeId = destinationNodeId
        )
    }

    fun getCurrentRoute(): List<GraphNode> {
        return currentRoute
    }

    fun clearRoute() {
        currentRoute = emptyList()
    }
}