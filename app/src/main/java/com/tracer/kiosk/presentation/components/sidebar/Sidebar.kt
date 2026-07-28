package com.tracer.kiosk.presentation.components.sidebar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Sidebar() {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(vertical = 40.dp),

        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(32.dp))

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