package com.tracer.kiosk.presentation.feature.navigation.components.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.presentation.feature.navigation.model.Destination

@Composable
fun SearchResults(
    destinations: List<Destination>,
    onDestinationClick: (Destination) -> Unit
) {

    if (destinations.isEmpty()) {

        Text(
            text = "No destinations found.",
            style = MaterialTheme.typography.bodyLarge
        )

        return
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(destinations) { destination ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onDestinationClick(destination)
                    },
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = destination.name,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = destination.category.name
                            .replace("_", " ")
                            .lowercase()
                            .replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}