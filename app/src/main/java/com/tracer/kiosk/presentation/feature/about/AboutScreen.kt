package com.tracer.kiosk.presentation.feature.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tracer.kiosk.presentation.components.about.AboutHeaderCard
import com.tracer.kiosk.presentation.components.about.AppInfoCard
import com.tracer.kiosk.presentation.components.about.DeveloperCard
import com.tracer.kiosk.presentation.components.about.GuideCard
import com.tracer.kiosk.presentation.components.common.TracerBackground
import com.tracer.kiosk.presentation.components.sidebar.Sidebar
import com.tracer.kiosk.presentation.navigation.Screen

@Composable
fun AboutScreen(
    navController: NavHostController
) {

    TracerBackground {

        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            // Sidebar
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(260.dp)
            ) {

                Sidebar(
                    selectedRoute = Screen.About.route,

                    onHomeClick = {
                        navController.navigate(Screen.Home.route)
                    },

                    onNavigateClick = {
                        navController.navigate(Screen.Navigation.route)
                    },

                    onFacultyClick = {
                        navController.navigate(Screen.Faculty.route)
                    },

                    onDepartmentClick = {
                        navController.navigate(Screen.Department.route)
                    },

                    onAboutClick = {}
                )
            }

            // Main Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(32.dp)
            ) {

                // Page Title
                Text(
                    text = "About",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Header
                AboutHeaderCard()

                Spacer(modifier = Modifier.height(24.dp))

                // Guide + App Information
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    GuideCard(
                        modifier = Modifier.weight(2f)
                    )

                    Spacer(modifier = Modifier.width(24.dp))

                    Box(
                        modifier = Modifier
                            .width(2.dp)
                            .height(180.dp)
                            .background(
                                MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
                            )
                    )

                    Spacer(modifier = Modifier.width(24.dp))

                    AppInfoCard(
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Developer Card (Full Width)
                DeveloperCard(
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "© 2026 Team Tracer",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.65f),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}