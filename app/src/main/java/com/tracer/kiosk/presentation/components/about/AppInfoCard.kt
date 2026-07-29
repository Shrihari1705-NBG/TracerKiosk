package com.tracer.kiosk.presentation.components.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun AppInfoCard(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .padding(12.dp),

        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Version 1.0.0",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = buildAnnotatedString {

                append("Tracer Kiosk is an environment of ")

                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("\"Tracer\"")
                }

                append(" which helps to navigate around department and Helpdesk at the Department.")

            },
            style = MaterialTheme.typography.bodyLarge
        )

    }

}