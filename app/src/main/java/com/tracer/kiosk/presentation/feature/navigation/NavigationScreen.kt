package com.tracer.kiosk.presentation.feature.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tracer.kiosk.presentation.components.navigation.NavigationTopBar
import com.tracer.kiosk.presentation.feature.navigation.algorithm.PathFinder
import com.tracer.kiosk.presentation.feature.navigation.components.destination.DestinationInfoCard
import com.tracer.kiosk.presentation.feature.navigation.components.destination.DestinationPanel
import com.tracer.kiosk.presentation.feature.navigation.components.map.MapCanvas
import com.tracer.kiosk.presentation.feature.navigation.components.search.CategoryGrid
import com.tracer.kiosk.presentation.feature.navigation.components.search.SearchBar
import com.tracer.kiosk.presentation.feature.navigation.components.search.SearchResults
import com.tracer.kiosk.presentation.feature.navigation.data.DestinationRepository
import com.tracer.kiosk.presentation.feature.navigation.graph.GraphNode
import com.tracer.kiosk.presentation.feature.navigation.model.Destination
import com.tracer.kiosk.presentation.feature.navigation.model.DestinationCategory
import com.tracer.kiosk.presentation.navigation.Screen

@Composable
fun NavigationScreen(
    navController: NavHostController
) {

    var query by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf<DestinationCategory?>(null)
    }

    var selectedDestination by remember {
        mutableStateOf<Destination?>(null)
    }

    var currentRoute by remember {
        mutableStateOf<List<GraphNode>>(emptyList())
    }

    val destinations = remember(query, selectedCategory) {

        DestinationRepository.destinations.filter { destination ->

            val matchesCategory =
                selectedCategory == null ||
                        destination.category == selectedCategory

            val matchesSearch =
                query.isBlank() ||
                        destination.name.contains(query, ignoreCase = true) ||
                        destination.aliases.any {
                            it.contains(query, ignoreCase = true)
                        }

            matchesCategory && matchesSearch

        }

    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            NavigationTopBar(
                onBackClick = {
                    navController.navigate(Screen.Home.route)
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp)
            ) {

                // Left Panel
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.30f)
                        .padding(20.dp)
                ) {

                    if (selectedCategory == null) {

                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            Text(
                                text = "Where would you like to go?",
                                style = MaterialTheme.typography.headlineMedium
                            )

                            SearchBar(
                                query = query,
                                onQueryChange = {
                                    query = it
                                }
                            )

                            HorizontalDivider()

                            if (query.isBlank()) {

                                CategoryGrid(
                                    onCategoryClick = {
                                        selectedCategory = it
                                    }
                                )

                            } else {

                                SearchResults(
                                    destinations = destinations,
                                    onDestinationClick = { destination ->
                                        selectedDestination = destination
                                    }
                                )

                            }

                        }

                    } else {

                        DestinationPanel(
                            category = selectedCategory!!,
                            destinations = destinations,
                            onBackClick = {
                                selectedCategory = null
                            },
                            onDestinationClick = { destination ->
                                selectedDestination = destination
                            }
                        )

                    }

                }

                // Right Panel
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.70f)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    selectedDestination?.let { destination ->

                        DestinationInfoCard(
                            destination = destination,
                            onStartNavigation = {

                                currentRoute = PathFinder.findPath(
                                    startNodeId = "N1",
                                    destinationNodeId = destination.nodeId
                                )

                                android.util.Log.d("Tracer", "Destination = ${destination.name}")
                                android.util.Log.d("Tracer", "Node = ${destination.nodeId}")
                                android.util.Log.d("Tracer", "Route Size = ${currentRoute.size}")
                                android.util.Log.d("Tracer", "Route = ${currentRoute.map { it.id }}")

                            }
                        )

                    }

                    Box(
                        modifier = Modifier.weight(1f)
                    ) {

                        MapCanvas(
                            route = currentRoute
                        )

                    }

                }

            }

        }

    }

}