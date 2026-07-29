package com.tracer.kiosk.presentation.components.department

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.presentation.theme.CardBlue

@Composable
fun MissionCard(
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardBlue
        )
    ) {

        Column(
            modifier = Modifier.padding(
                horizontal = 32.dp,
                vertical = 28.dp
            )
        ) {

            Text(
                text = "Mission",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "• Impart quality technical education in the field of Electronics and Communication Engineering through effective teaching-learning practices.",
                modifier = Modifier.padding(top = 20.dp),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = MaterialTheme.typography.headlineSmall.lineHeight
            )

            Text(
                text = "• Encourage innovation, research, and project-based learning to develop competent professionals with problem-solving abilities.",
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = MaterialTheme.typography.headlineSmall.lineHeight
            )

            Text(
                text = "• Instill professional ethics, leadership qualities, teamwork, and lifelong learning to serve society effectively.",
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = MaterialTheme.typography.headlineSmall.lineHeight
            )

        }

    }

}