package com.tracer.kiosk.presentation.feature.navigation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Lan
import androidx.compose.material.icons.outlined.LocalLibrary
import androidx.compose.material.icons.outlined.MeetingRoom
import androidx.compose.material.icons.outlined.School
import androidx.compose.ui.graphics.vector.ImageVector

enum class DestinationCategory(
    val title: String,
    val icon: ImageVector
) {

    FACULTY(
        title = "Faculty & Staff",
        icon = Icons.Outlined.Groups
    ),

    LABORATORY(
        title = "Laboratories",
        icon = Icons.Outlined.Lan
    ),

    CLASSROOM(
        title = "Classrooms",
        icon = Icons.Outlined.School
    ),

    OFFICE(
        title = "Offices",
        icon = Icons.Outlined.Apartment
    ),

    FACILITY(
        title = "Facilities",
        icon = Icons.Outlined.LocalLibrary
    )

}