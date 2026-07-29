package com.tracer.kiosk.presentation.components.idle

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import com.tracer.kiosk.presentation.feature.idle.IdleScreen
import kotlinx.coroutines.delay

private const val IDLE_TIMEOUT = 2*60*1000L

@Composable
fun IdleContainer(
    content: @Composable () -> Unit
) {

    var showIdleScreen by remember { mutableStateOf(false) }
    var lastInteraction by remember {
        mutableLongStateOf(System.currentTimeMillis())
    }

    LaunchedEffect(lastInteraction) {

        while (true) {

            delay(1000)

            if (
                !showIdleScreen &&
                System.currentTimeMillis() - lastInteraction >= IDLE_TIMEOUT
            ) {
                showIdleScreen = true
                break
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(showIdleScreen) {

                awaitEachGesture {

                    awaitPointerEvent(pass = PointerEventPass.Initial)

                    lastInteraction = System.currentTimeMillis()

                    if (showIdleScreen) {
                        showIdleScreen = false
                    }
                }
            }
    ) {

        content()

        AnimatedVisibility(
            visible = showIdleScreen,
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            IdleScreen()

        }

    }
}