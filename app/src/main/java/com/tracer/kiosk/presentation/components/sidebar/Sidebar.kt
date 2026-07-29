package com.tracer.kiosk.presentation.components.sidebar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Sidebar() {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(
                    topEnd = 64.dp,
                    bottomEnd = 64.dp
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
                selected = true,
                onClick = {}
            )

            SidebarItem(
                title = "Navigate",
                selected = false,
                onClick = {}
            )

            SidebarItem(
                title = "Faculty & Staff",
                selected = false,
                onClick = {}
            )

            SidebarItem(
                title = "Department",
                selected = false,
                onClick = {}
            )

            SidebarItem(
                title = "About Tracer",
                selected = false,
                onClick = {}
            )

        }

    }

}