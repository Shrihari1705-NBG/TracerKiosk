package com.tracer.kiosk.presentation.feature.navigation.components.destination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.presentation.feature.navigation.model.Destination

@Composable
fun DestinationInfoCard(
    destination: Destination,
    onStartNavigation: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "Selected Destination",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = destination.name,
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = destination.category.name
                    .replace("_", " ")
                    .lowercase()
                    .replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onStartNavigation
            ) {
                Text("Start Navigation")
            }
        }
    }
}