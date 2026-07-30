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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.R
import com.tracer.kiosk.presentation.feature.navigation.data.GraphRepository
import com.tracer.kiosk.presentation.feature.navigation.graph.GraphNode
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import android.graphics.PathMeasure
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import android.graphics.Paint
import android.graphics.Typeface


private const val ORIGINAL_WIDTH = 1280f
private const val ORIGINAL_HEIGHT = 720f

private const val SHOW_GRAPH_DEBUG = false

// Fine-tune graph alignment
private const val GRAPH_OFFSET_X = 5f
private const val GRAPH_OFFSET_Y = 3f

@Composable
fun MapCanvas(
    route: List<GraphNode>
) {
    val routeProgress = remember {
        Animatable(0f)
    }

    val blinkTransition = rememberInfiniteTransition(label = "blink")

    val textAlpha = blinkTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.25f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 700,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "textAlpha"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "kioskPulse")

    val kioskPulse = infiniteTransition.animateFloat(
        initialValue = 18f,
        targetValue = 28f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1800,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "kioskRadius"
    )

    LaunchedEffect(route) {

        routeProgress.snapTo(0f)

        if (route.isNotEmpty()) {
            routeProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 1400,
                    easing = FastOutSlowInEasing
                )
            )
        }

    }

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

            // =====================================================
            // Debug Graph
            // =====================================================
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

            // =====================================================
// Route (Smooth Curves)
// =====================================================
            if (route.size >= 2) {

                val path = Path()

                val start = transform(route.first().position)
                path.moveTo(start.x, start.y)

                for (i in 1 until route.size) {

                    val previous = transform(route[i - 1].position)
                    val current = transform(route[i].position)

                    val midX = (previous.x + current.x) / 2f
                    val midY = (previous.y + current.y) / 2f

                    path.quadraticBezierTo(
                        previous.x,
                        previous.y,
                        midX,
                        midY
                    )

                }

                val end = transform(route.last().position)
                path.lineTo(end.x, end.y)
                val androidMeasure = PathMeasure(
                    path.asAndroidPath(),
                    false
                )

                val position = FloatArray(2)
                val tangent = FloatArray(2)

                val animatedAndroidPath = android.graphics.Path()

                androidMeasure.getSegment(
                    0f,
                    androidMeasure.length * routeProgress.value,
                    animatedAndroidPath,
                    true
                )

                androidMeasure.getPosTan(
                    androidMeasure.length * routeProgress.value,
                    position,
                    tangent
                )

                val animatedPath =
                    animatedAndroidPath.asComposePath()

                // Glow
                drawPath(
                    path = animatedPath,
                    color = Color(0x55CC6600),
                    style = Stroke(
                        width = 24f,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )

                // Main Route
                drawPath(
                    path = animatedPath,
                    color = Color(0xFFCC6600),
                    style = Stroke(
                        width = 16f,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )

                // Navigation Dot Glow
                drawCircle(
                    color = Color.White,
                    radius = 18f,
                    center = Offset(position[0], position[1])
                )

                // Navigation Dot
                drawCircle(
                    color = Color(0xC802022A),
                    radius = 8f,
                    center = Offset(position[0], position[1])
                )
            }

            // =====================================================
// You Are Here Marker (N1)
// =====================================================
            val kiosk = transform(
                GraphRepository.nodes.first { it.id == "N1" }.position
            )

// Outer glow
            drawCircle(
                color = Color(0x33FF0000),
                radius = 24f,
                center = kiosk
            )

// Breathing Glow
            drawCircle(
                color = Color(0x33FF0000),
                radius = kioskPulse.value,
                center = kiosk
            )

// Outer Ring
            drawCircle(
                color = Color.White,
                radius = 16f,
                center = kiosk
            )

// Main Marker
            drawCircle(
                color = Color.Red,
                radius = 12f,
                center = kiosk
            )

// Center Dot
            drawCircle(
                color = Color.White,
                radius = 4f,
                center = kiosk
            )

            drawIntoCanvas { canvas ->

                val fillPaint = Paint().apply {
                    color = android.graphics.Color.RED
                    style = Paint.Style.FILL
                    textSize = 16f
                    textAlign = Paint.Align.CENTER
                    typeface = Typeface.DEFAULT_BOLD
                    alpha = (255 * textAlpha.value).toInt()
                    isAntiAlias = true
                }

                canvas.nativeCanvas.drawText(
                    "You Are Here",
                    kiosk.x,
                    kiosk.y + 45f,
                    fillPaint
                )
            }

// =====================================================
// Destination Marker
// =====================================================
            if (route.isNotEmpty()) {

                val destination = transform(route.last().position)

                // Glow
                drawCircle(
                    color = Color(0x33CC6600),
                    radius = 26f,
                    center = destination
                )

                // Main destination
                drawCircle(
                    color = Color(0xFFCC6600),
                    radius = 16f,
                    center = destination
                )

                // Inner circle
                drawCircle(
                    color = Color.White,
                    radius = 7f,
                    center = destination
                )
            }
        }
    }
}