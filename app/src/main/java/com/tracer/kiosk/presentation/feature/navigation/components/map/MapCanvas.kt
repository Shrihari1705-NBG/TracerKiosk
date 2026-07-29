package com.tracer.kiosk.presentation.feature.navigation.components.map

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.R
import com.tracer.kiosk.presentation.feature.navigation.algorithm.RouteGenerator
import com.tracer.kiosk.presentation.feature.navigation.data.GraphRepository
import com.tracer.kiosk.presentation.feature.navigation.graph.GraphNode


@Composable
fun MapCanvas(
    route: List<GraphNode>
) {

    var draggingNode by remember {
        mutableStateOf<GraphNode?>(null)
    }

    var selectedNode by remember {
        mutableStateOf<GraphNode?>(null)
    }

    // Current marker position
    var markerPosition by remember {
        mutableStateOf(
            Offset(
                x = 520f,
                y = 120f
            )
        )
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline
            )

            // Tap Gesture
            .pointerInput(Unit) {

                detectTapGestures { tapOffset ->

                    val tappedNode = GraphRepository.nodes.find { node ->

                        val dx = node.position.x - tapOffset.x
                        val dy = node.position.y - tapOffset.y

                        (dx * dx + dy * dy) <= 25f * 25f

                    }

                    if (tappedNode != null) {

                        if (selectedNode == null) {

                            selectedNode = tappedNode
                            tappedNode.isSelected = true

                            Log.d("GraphEditor", "Selected ${tappedNode.id}")

                        } else if (selectedNode != tappedNode) {

                            if (!selectedNode!!.neighbors.contains(tappedNode.id)) {

                                selectedNode!!.neighbors.add(tappedNode.id)
                                tappedNode.neighbors.add(selectedNode!!.id)

                                Log.d(
                                    "GraphEditor",
                                    "Connected ${selectedNode!!.id} ↔ ${tappedNode.id}"
                                )
                            }

                            selectedNode!!.isSelected = false
                            selectedNode = null

                        }

                    } else {

                        GraphRepository.nodes.add(
                            GraphNode(
                                id = "N${GraphRepository.nodes.size + 1}",
                                position = tapOffset
                            )
                        )

                    }

                }

            }

            // Long Press + Drag
            .pointerInput(Unit) {

                detectDragGesturesAfterLongPress(

                    onDragStart = { offset ->

                        draggingNode = GraphRepository.nodes.find { node ->

                            val dx = node.position.x - offset.x
                            val dy = node.position.y - offset.y

                            (dx * dx + dy * dy) <= 25f * 25f

                        }

                    },

                    onDrag = { change, _ ->

                        draggingNode?.position = change.position

                    },

                    onDragEnd = {

                        draggingNode = null

                    },

                    onDragCancel = {

                        draggingNode = null

                    }

                )

            }

    ) {

        // Floor Map
        Image(
            painter = painterResource(R.drawable.floor_map),
            contentDescription = "Department Floor Map",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            // -----------------------------
            // Draw Graph Edges
            // -----------------------------
            GraphRepository.nodes.forEach { node ->

                node.neighbors.forEach { neighborId ->

                    val neighbor = GraphRepository.nodes.find {
                        it.id == neighborId
                    } ?: return@forEach

                    if (node.id < neighbor.id) {

                        drawLine(
                            color = Color(0xFF1976D2),
                            start = node.position,
                            end = neighbor.position,
                            strokeWidth = 6f,
                            cap = StrokeCap.Round
                        )

                    }

                }

            }

            // -----------------------------
            // Draw Shortest Route
            // -----------------------------
            for (i in 0 until route.size - 1) {

                drawLine(
                    color = Color.Red,
                    start = route[i].position,
                    end = route[i + 1].position,
                    strokeWidth = 10f,
                    cap = StrokeCap.Round
                )

            }

        }

        // Current Location Marker
        CurrentLocationMarker(
            position = markerPosition,
            onPositionChanged = { newPosition ->
                markerPosition = newPosition
            }
        )

        // Graph Nodes
        GraphRepository.nodes.forEach { node ->

            NodeMarker(
                position = node.position,
                isSelected = node.isSelected
            )

        }

    }

}