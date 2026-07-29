package com.tracer.kiosk.presentation.feature.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Map
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tracer.kiosk.presentation.components.card.FeatureCard
import com.tracer.kiosk.presentation.components.common.SectionTitle
import com.tracer.kiosk.presentation.components.common.TracerBackground
import com.tracer.kiosk.presentation.components.sidebar.Sidebar
import com.tracer.kiosk.presentation.components.topbar.AppHeader
import com.tracer.kiosk.presentation.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavHostController
) {

    val infiniteTransition = rememberInfiniteTransition(label = "headerFloat")

    val floatOffset by infiniteTransition.animateFloat(
        initialValue = -6f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "float"
    )

    TracerBackground {

        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            Sidebar(
                selectedRoute = Screen.Home.route,

                onHomeClick = {},

                onNavigateClick = {
                    navController.navigate(Screen.Navigation.route)
                },

                onFacultyClick = {
                    navController.navigate(Screen.Faculty.route)
                },

                onDepartmentClick = {
                    navController.navigate(Screen.Department.route)
                },

                onAboutClick = {
                    navController.navigate(Screen.About.route)
                }
            )

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

                Box(
                    modifier = Modifier.graphicsLayer {
                        translationY = floatOffset
                    }
                ) {
                    AppHeader()
                }

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
                            icon = Icons.Outlined.Map,
                            onClick = {
                                navController.navigate(Screen.Navigation.route)
                            },
                            modifier = Modifier.weight(1f)
                        )
                        FeatureCard(
                            title = "Faculty & Staff",
                            icon = Icons.Outlined.Groups,
                            onClick = {
                                navController.navigate(Screen.Faculty.route)
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {

                        FeatureCard(
                            title = "Department",
                            icon = Icons.Outlined.AccountBalance,
                            onClick = {
                                navController.navigate(Screen.Department.route)
                            },
                            modifier = Modifier.weight(1f)
                        )

                        FeatureCard(
                            title = "About Tracer",
                            icon = Icons.Outlined.Info,
                            onClick = {
                                navController.navigate(Screen.About.route)
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}