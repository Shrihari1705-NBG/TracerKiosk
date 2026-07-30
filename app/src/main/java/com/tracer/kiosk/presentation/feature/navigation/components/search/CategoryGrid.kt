package com.tracer.kiosk.presentation.feature.navigation.components.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.presentation.feature.navigation.model.DestinationCategory
import androidx.compose.ui.graphics.Color

@Composable
fun CategoryGrid(
    onCategoryClick: (DestinationCategory) -> Unit
) {

    val categories = DestinationCategory.entries

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        items(categories) { category ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onCategoryClick(category)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF071B4D)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = category.icon,
                        contentDescription = category.title,
                        tint = Color.White
                    )

                    Text(
                        text = category.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                }

            }

        }

    }

}