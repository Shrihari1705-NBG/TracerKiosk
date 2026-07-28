package com.tracer.kiosk.presentation.components.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.R

@Composable
fun AppHeader(

    modifier: Modifier = Modifier

) {

    Row(
        modifier = modifier.fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically,

        horizontalArrangement = Arrangement.Center

    ) {

        Image(
            painter = painterResource(id = R.drawable.tracer_logo),
            contentDescription = "Tracer Logo",

            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.width(32.dp))

        Column {

            Text(
                text = "Tracer Kiosk",

                style = MaterialTheme.typography.displayLarge,

                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Indoor Navigation for Smart Campuses",

                style = MaterialTheme.typography.bodyLarge
            )

        }

    }

}