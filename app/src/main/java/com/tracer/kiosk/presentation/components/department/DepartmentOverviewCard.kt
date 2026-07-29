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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.presentation.theme.CardBlue

@Composable
fun DepartmentOverviewCard(
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
                text = "The Department of Electronics & Communication Engineering (E&CE), established in 2004, offers B.E. and M.Tech. programs and is a VTU-recognized research centre. With experienced faculty, modern laboratories, industry collaborations, and an Advanced Robotics Lab under IIT Bombay's e-Yantra initiative, the department emphasizes project-based learning, research, and innovation. Students gain strong technical skills and secure placements in leading companies such as Infosys, Wipro, Cadence, and Sankalp Semiconductors.",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Justify,
                lineHeight = MaterialTheme.typography.headlineSmall.lineHeight
            )

        }

    }

}