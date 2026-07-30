package com.tracer.kiosk.presentation.components.sidebar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.presentation.navigation.Screen

@Composable
fun Sidebar(
    selectedRoute: String,
    onHomeClick: () -> Unit,
    onNavigateClick: () -> Unit,
    onFacultyClick: () -> Unit,
    onDepartmentClick: () -> Unit,
    onAboutClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .width(240.dp)
            .fillMaxHeight()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF071B4D),
                        Color(0xFF123C82)
                    )
                )
            )
            .padding(vertical = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SidebarItem(
                title = "Home",
                selected = selectedRoute == Screen.Home.route,
                onClick = onHomeClick
            )

            SidebarItem(
                title = "Navigate",
                selected = selectedRoute == Screen.Navigation.route,
                onClick = onNavigateClick
            )

            SidebarItem(
                title = "Faculty & Staff",
                selected = selectedRoute == Screen.Faculty.route,
                onClick = onFacultyClick
            )

            SidebarItem(
                title = "Department",
                selected = selectedRoute == Screen.Department.route,
                onClick = onDepartmentClick
            )

            SidebarItem(
                title = "About Tracer",
                selected = selectedRoute == Screen.About.route,
                onClick = onAboutClick
            )
        }
    }
}