package com.tracer.kiosk.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val TracerShapes = Shapes(

    // Small components
    small = RoundedCornerShape(8.dp),

    // Cards, Buttons, Search Bars
    medium = RoundedCornerShape(16.dp),

    // Dialogs, Large Cards
    large = RoundedCornerShape(24.dp),

    // Extra Large Containers
    extraLarge = RoundedCornerShape(32.dp)
)