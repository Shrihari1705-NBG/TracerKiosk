package com.tracer.kiosk.presentation.feature.navigation.components.map

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.R
import com.tracer.kiosk.presentation.feature.navigation.data.GraphRepository
import com.tracer.kiosk.presentation.feature.navigation.graph.GraphNode

private const val ORIGINAL_WIDTH = 1280f
private const val ORIGINAL_HEIGHT = 720f

private const val SHOW_GRAPH_DEBUG = false

// Fine-tune the graph alignment
private const val GRAPH_OFFSET_X = 5f
private const val GRAPH_OFFSET_Y = 3f

@Composable
fun MapCanvas(
    route: List<GraphNode>
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline
            )
    ) {

        Image(
            painter = painterResource(R.drawable.floor_map),
            contentDescription = "Department Floor Map",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            val scale = minOf(
                size.width / ORIGINAL_WIDTH,
                size.height / ORIGINAL_HEIGHT
            )

            val imageWidth = ORIGINAL_WIDTH * scale
            val imageHeight = ORIGINAL_HEIGHT * scale

            val offsetX = (size.width - imageWidth) / 2f
            val offsetY = (size.height - imageHeight) / 2f



            fun transform(point: Offset): Offset {
                return Offset(
                    x = offsetX + point.x * scale + GRAPH_OFFSET_X,
                    y = offsetY + point.y * scale + GRAPH_OFFSET_Y
                )
            }

            // ==========================
            // Debug Graph
            // ==========================
            if (SHOW_GRAPH_DEBUG) {

                GraphRepository.nodes.forEach { node ->

                    node.neighbors.forEach { neighborId ->

                        val neighbor = GraphRepository.nodes.find {
                            it.id == neighborId
                        } ?: return@forEach

                        if (node.id < neighbor.id) {

                            drawLine(
                                color = Color.Blue,
                                start = transform(node.position),
                                end = transform(neighbor.position),
                                strokeWidth = 5f,
                                cap = StrokeCap.Round
                            )

                        }

                    }

                }

            }

            // ==========================
            // Route
            // ==========================
            for (i in 0 until route.size - 1) {

                drawLine(
                    color = Color.Red,
                    start = transform(route[i].position),
                    end = transform(route[i + 1].position),
                    strokeWidth = 8f,
                    cap = StrokeCap.Round
                )

            }

            // ==========================
            // Kiosk Marker
            // ==========================
            val kiosk = transform(
                GraphRepository.nodes.first { it.id == "N1" }.position
            )

            drawCircle(
                color = Color.Red,
                radius = 12f,
                center = kiosk
            )

            drawCircle(
                color = Color.White,
                radius = 5f,
                center = kiosk
            )
        }
    }
}