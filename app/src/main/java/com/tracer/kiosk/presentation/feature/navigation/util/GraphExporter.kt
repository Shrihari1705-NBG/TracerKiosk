package com.tracer.kiosk.presentation.feature.navigation.util

import android.util.Log
import com.tracer.kiosk.presentation.feature.navigation.data.GraphRepository

object GraphExporter {

    private const val TAG = "GraphExporter"

    fun export(): String  {

        val builder = StringBuilder()

        builder.appendLine("object GraphRepository {")
        builder.appendLine()
        builder.appendLine("    val nodes = mutableStateListOf(")
        builder.appendLine()

        GraphRepository.nodes.forEachIndexed { index, node ->

            builder.appendLine("        GraphNode(")
            builder.appendLine("            id = \"${node.id}\",")

            val x = node.position.x.toInt()
            val y = node.position.y.toInt()

            builder.appendLine(
                "            position = Offset(${x}f, ${y}f),"
            )

            builder.append(
                "            neighbors = listOf("
            )

            node.neighbors.forEachIndexed { neighborIndex, neighbor ->

                builder.append("\"$neighbor\"")

                if (neighborIndex != node.neighbors.lastIndex) {
                    builder.append(", ")
                }

            }

            builder.appendLine(")")
            builder.append("        )")

            if (index != GraphRepository.nodes.lastIndex) {
                builder.append(",")
            }

            builder.appendLine()
            builder.appendLine()

        }

        builder.appendLine("    )")
        builder.appendLine("}")

        val graph = builder.toString()

        Log.d(TAG, graph)

        return graph

    }

}