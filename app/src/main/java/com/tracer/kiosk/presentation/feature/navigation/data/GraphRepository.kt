package com.tracer.kiosk.presentation.feature.navigation.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Offset
import com.tracer.kiosk.presentation.feature.navigation.graph.GraphNode

object GraphRepository {

    val nodes = mutableStateListOf(

        GraphNode(
            id = "N1",
            position = Offset(550f, 180f)
        )

    )

}