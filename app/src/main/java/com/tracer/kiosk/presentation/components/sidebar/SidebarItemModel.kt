package com.tracer.kiosk.presentation.components.sidebar

import androidx.compose.ui.graphics.vector.ImageVector

data class SidebarItemModel(
    val title: String,
    val icon: ImageVector,
    val route: String
)