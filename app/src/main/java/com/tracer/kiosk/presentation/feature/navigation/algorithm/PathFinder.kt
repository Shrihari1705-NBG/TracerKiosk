package com.tracer.kiosk.presentation.feature.navigation.algorithm

import com.tracer.kiosk.presentation.feature.navigation.data.GraphRepository
import com.tracer.kiosk.presentation.feature.navigation.graph.GraphNode

object PathFinder {

    fun findPath(
        startNodeId: String,
        destinationNodeId: String
    ): List<GraphNode> {

        val nodes = GraphRepository.nodes

        val startNode = nodes.find {
            it.id == startNodeId
        } ?: return emptyList()

        val destinationNode = nodes.find {
            it.id == destinationNodeId
        } ?: return emptyList()

        return AStarAlgorithm.findPath(
            start = startNode,
            goal = destinationNode,
            allNodes = nodes
        )

    }

}