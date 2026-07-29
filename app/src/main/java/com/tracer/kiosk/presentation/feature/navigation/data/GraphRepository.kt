package com.tracer.kiosk.presentation.feature.navigation.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Offset
import com.tracer.kiosk.presentation.feature.navigation.graph.GraphNode

object GraphRepository {

    val nodes = mutableStateListOf(

        GraphNode(
            id = "N1",
            position = Offset(755f, 244f),
            neighbors = listOf("N2")
        ),

        GraphNode(
            id = "N2",
            position = Offset(820f, 369f),
            neighbors = listOf("N1", "N3", "N4", "N5", "N10")
        ),

        GraphNode(
            id = "N3",
            position = Offset(881f, 278f),
            neighbors = listOf("N2")
        ),

        GraphNode(
            id = "N4",
            position = Offset(888f, 461f),
            neighbors = listOf("N2")
        ),

        GraphNode(
            id = "N5",
            position = Offset(983f, 334f),
            neighbors = listOf("N2", "N6")
        ),

        GraphNode(
            id = "N6",
            position = Offset(1106f, 269f),
            neighbors = listOf("N5", "N7")
        ),

        GraphNode(
            id = "N7",
            position = Offset(1134f, 425f),
            neighbors = listOf("N6", "N8")
        ),

        GraphNode(
            id = "N8",
            position = Offset(1130f, 508f),
            neighbors = listOf("N7", "N9")
        ),

        GraphNode(
            id = "N9",
            position = Offset(1080f, 648f),
            neighbors = listOf("N8")
        ),

        GraphNode(
            id = "N10",
            position = Offset(675f, 309f),
            neighbors = listOf("N2", "N11", "N12")
        ),

        GraphNode(
            id = "N11",
            position = Offset(627f, 215f),
            neighbors = listOf("N10")
        ),

        GraphNode(
            id = "N12",
            position = Offset(555f, 423f),
            neighbors = listOf("N10", "N13", "N14")
        ),

        GraphNode(
            id = "N13",
            position = Offset(328f, 364f),
            neighbors = listOf("N12", "N15", "N16", "N27")
        ),

        GraphNode(
            id = "N14",
            position = Offset(315f, 455f),
            neighbors = listOf("N12", "N18", "N19", "N21", "N22")
        ),

        GraphNode(
            id = "N15",
            position = Offset(375f, 311f),
            neighbors = listOf("N13")
        ),

        GraphNode(
            id = "N16",
            position = Offset(342f, 201f),
            neighbors = listOf("N13", "N17")
        ),

        GraphNode(
            id = "N17",
            position = Offset(348f, 136f),
            neighbors = listOf("N16")
        ),

        GraphNode(
            id = "N18",
            position = Offset(374f, 555f),
            neighbors = listOf("N14")
        ),

        GraphNode(
            id = "N19",
            position = Offset(332f, 661f),
            neighbors = listOf("N14", "N20")
        ),

        GraphNode(
            id = "N20",
            position = Offset(336f, 720f),
            neighbors = listOf("N19")
        ),

        GraphNode(
            id = "N21",
            position = Offset(207f, 610f),
            neighbors = listOf("N14")
        ),

        GraphNode(
            id = "N22",
            position = Offset(212f, 500f),
            neighbors = listOf("N14", "N23", "N25", "N26")
        ),

        GraphNode(
            id = "N23",
            position = Offset(124f, 495f),
            neighbors = listOf("N22", "N24")
        ),

        GraphNode(
            id = "N24",
            position = Offset(54f, 466f),
            neighbors = listOf("N23")
        ),

        GraphNode(
            id = "N25",
            position = Offset(117f, 418f),
            neighbors = listOf("N22")
        ),

        GraphNode(
            id = "N26",
            position = Offset(186f, 424f),
            neighbors = listOf("N22")
        ),

        GraphNode(
            id = "N27",
            position = Offset(221f, 318f),
            neighbors = listOf("N13")
        )

    )
}