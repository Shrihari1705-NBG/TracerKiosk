package com.tracer.kiosk.presentation.feature.navigation.components.destination

import androidx.compose.foundation.clickable
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
fun DestinationList(
    destinations: List<Destination>,
    onDestinationClick: (Destination) -> Unit
) {

    LazyColumn {

        items(destinations) { destination ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        onDestinationClick(destination)
                    },
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {

                Text(
                    text = destination.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(20.dp)
                )

            }

        }

    }

}