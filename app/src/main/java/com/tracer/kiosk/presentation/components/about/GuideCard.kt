package com.tracer.kiosk.presentation.components.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.R


@Composable
fun GuideCard(
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .height(250.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(24.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        // Faculty Image
        Image(
            painter = painterResource(R.drawable.faculty_vijayalaxmi_kalal),
            contentDescription = "Guide",

            modifier = Modifier
                .width(170.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(12.dp)),

            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(32.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Prof. Vijayalaxmi Kalal",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Guide for project \"Tracer\"",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(28.dp))

            Surface(
                shape = RoundedCornerShape(50.dp),
                color = MaterialTheme.colorScheme.surface
            ) {

                Text(
                    text = "Assistant Professor",

                    modifier = Modifier.padding(
                        horizontal = 32.dp,
                        vertical = 12.dp
                    ),

                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )

            }

        }

    }

}