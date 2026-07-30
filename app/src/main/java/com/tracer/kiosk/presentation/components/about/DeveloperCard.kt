package com.tracer.kiosk.presentation.components.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DeveloperCard(
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            Text(
                text = "Shrihari N B Goudru",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Student Android Developer",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White.copy(alpha = 0.90f),
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Designed and developed the Tracer Kiosk Android application for smart campus navigation. Implemented the application architecture, modern Jetpack Compose user interface, indoor navigation engine, A* pathfinding implementation, interactive floor map visualization, and the complete kiosk navigation experience.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.95f)
            )

            Text(
                text = "Technologies: Kotlin • Jetpack Compose • MVVM • A* Pathfinding • Material 3",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}