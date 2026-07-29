package com.tracer.kiosk.presentation.components.about

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.presentation.components.topbar.AppHeader
import com.tracer.kiosk.presentation.theme.CardBlue

@Composable
fun AboutHeaderCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardBlue
        )
    ) {

        AppHeader(
            modifier = Modifier.padding(24.dp)
        )

    }

}