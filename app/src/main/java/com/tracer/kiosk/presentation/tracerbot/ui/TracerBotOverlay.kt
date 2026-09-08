package com.tracer.kiosk.presentation.tracerbot.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tracer.kiosk.R
import com.tracer.kiosk.presentation.tracerbot.engine.TracerBotEngine
import com.tracer.kiosk.presentation.tracerbot.viewmodel.TracerBotViewModel
import com.tracer.kiosk.presentation.tracerbot.viewmodel.TracerBotViewModelFactory

/**
 * Floating TracerBot assistant.
 *
 * Behaviour:
 *
 * 1. Shows a TracerBot avatar at the bottom-left.
 * 2. Avatar continuously floats gently up and down.
 * 3. Tapping the avatar opens the assistant panel.
 * 4. The assistant panel contains TracerBotScreen.
 * 5. The X button inside TracerBotScreen closes the panel.
 * 6. The panel moves above the software keyboard when it appears.
 */
@Composable
fun TracerBotOverlay(
    tracerBotEngine: TracerBotEngine,
    modifier: Modifier = Modifier
) {

    // -------------------------------------------------------------
    // Bot open / closed state
    // -------------------------------------------------------------

    var isOpen by remember {
        mutableStateOf(false)
    }

    // -------------------------------------------------------------
    // ViewModel
    // -------------------------------------------------------------

    val viewModel: TracerBotViewModel = viewModel(
        factory = TracerBotViewModelFactory(
            tracerBotEngine = tracerBotEngine
        )
    )

    // -------------------------------------------------------------
    // Continuous floating animation
    // -------------------------------------------------------------

    val infiniteTransition = rememberInfiniteTransition(
        label = "TracerBotFloating"
    )

    val floatingOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -15f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "TracerBotFloat"
    )

    // -------------------------------------------------------------
    // Overlay container
    // -------------------------------------------------------------

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        // =========================================================
        // TracerBot assistant panel
        // =========================================================

        AnimatedVisibility(
            visible = isOpen,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = 24.dp,
                    bottom = 24.dp
                )
                .imePadding(),
            enter = fadeIn() +
                    slideInHorizontally(
                        initialOffsetX = { -it / 2 }
                    ),
            exit = fadeOut()
        ) {

            Surface(
                modifier = Modifier
                    .width(480.dp)
                    .fillMaxHeight(0.88f)
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(28.dp)
                    ),
                shape = RoundedCornerShape(28.dp)
            ) {

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    TracerBotScreen(
                        viewModel = viewModel,

                        // -------------------------------------------------
                        // IMPORTANT:
                        // Connect the X button to the overlay state.
                        // -------------------------------------------------

                        onClose = {
                            isOpen = false
                        }
                    )
                }
            }
        }

        // =========================================================
        // Floating TracerBot button
        // =========================================================

        AnimatedVisibility(
            visible = !isOpen,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = 64.dp,
                    bottom = 42.dp
                )
                .offset {
                    IntOffset(
                        x = 0,
                        y = floatingOffset.toInt()
                    )
                },
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = CircleShape
                    ),
                shape = CircleShape
            ) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    // -------------------------------------------------
                    // TracerBot avatar
                    // -------------------------------------------------

                    Image(
                        painter = painterResource(
                            id = R.drawable.tracerbot_avatar
                        ),
                        contentDescription = "Open TracerBot",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(3.dp),
                        contentScale = ContentScale.Fit
                    )

                    // -------------------------------------------------
                    // Transparent clickable layer
                    // -------------------------------------------------

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {

                                isOpen = true

                                viewModel.openBot()
                            }
                    )
                }
            }
        }
    }
}