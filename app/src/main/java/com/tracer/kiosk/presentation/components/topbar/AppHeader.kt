package com.tracer.kiosk.presentation.components.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.R
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun AppHeader(

    modifier: Modifier = Modifier

) {
    val infiniteTransition = rememberInfiniteTransition(label = "logoFloat")

    val floatOffset by infiniteTransition.animateFloat(
        initialValue = -8f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2500,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "float"
    )

    Row(
        modifier = modifier.fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically,

        horizontalArrangement = Arrangement.Center

    ) {

        Image(
            painter = painterResource(R.drawable.tracer_logo),
            contentDescription = "Tracer Logo",
            modifier = Modifier
                .size(190.dp)
                .graphicsLayer {
                    translationY = floatOffset
                }
        )

        Spacer(modifier = Modifier.width(32.dp))

        Column {

            Text(
                text = "Tracer Kiosk",

                style = MaterialTheme.typography.displayLarge,

                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Indoor Navigation for Smart Campuses",

                style = MaterialTheme.typography.bodyLarge
            )

        }

    }

}