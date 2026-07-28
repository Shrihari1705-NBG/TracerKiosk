package com.tracer.kiosk.presentation.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.presentation.components.card.FeatureCard
import com.tracer.kiosk.presentation.components.common.SectionTitle
import com.tracer.kiosk.presentation.components.topbar.AppHeader
import com.tracer.kiosk.presentation.components.sidebar.Sidebar

@Composable
fun HomeScreen() {

    Row(
        modifier = Modifier.fillMaxSize()
    ) {

        // Sidebar
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(260.dp)
        ) {
            Sidebar()
        }

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 32.dp,
                    top = 56.dp,
                    end = 32.dp,
                    bottom = 32.dp
                )
        ) {

            AppHeader()

            Spacer(modifier = Modifier.height(24.dp))

            SectionTitle(
                title = "What do you want to do?"
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    FeatureCard(
                        title = "Navigate",
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(24.dp))

                    FeatureCard(
                        title = "Faculty & Staff",
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    )

                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    FeatureCard(
                        title = "Department",
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(24.dp))

                    FeatureCard(
                        title = "About Tracer",
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    )

                }

            }

        }

    }

}