package com.tracer.kiosk.presentation.feature.navigation.components.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.R
import com.tracer.kiosk.presentation.feature.navigation.data.GraphRepository
import com.tracer.kiosk.presentation.feature.navigation.graph.GraphNode

@Composable
fun MapCanvas() {

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
            .pointerInput(Unit) {

                detectTapGestures { tapOffset ->

                    GraphRepository.nodes.add(
                        GraphNode(
                            id = "N${GraphRepository.nodes.size + 1}",
                            position = tapOffset
                        )
                    )

                }

            }
    ) {

        // Floor Map
        Image(
            painter = painterResource(R.drawable.floor_map),
            contentDescription = "Department Floor Map",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )

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
                position = node.position
            )

        }

    }
}