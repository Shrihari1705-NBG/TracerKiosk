package com.tracer.kiosk.presentation.components.faculty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.tracer.kiosk.R

@Composable
fun HodCard(

    modifier: Modifier = Modifier,

    hodName: String = "Dr. Plasin Francis Dias",

    designation: String = "Professor & Head",

    qualification: String = "M.Tech., Ph.D.",

    email: String = "pfd@klsvdit.edu.in",

    experience: String = "26 Years",

    publications: String = "18 Publications"

) {

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(36.dp),
        color = Color(0xFFB4C7E7)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            // Image Placeholder
            Image(
                painter = painterResource(
                    id = R.drawable.faculty_plasin_dias
                ),
                contentDescription = "HOD",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .width(190.dp)
                    .height(170.dp)
                    .clip(RoundedCornerShape(24.dp))
            )

            Spacer(modifier = Modifier.width(28.dp))

            // Details
            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "HEAD OF DEPARTMENT",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = hodName,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFFB02A1C),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = designation,
                    style = MaterialTheme.typography.headlineSmall,
                    fontStyle = FontStyle.Italic
                )

                Text(
                    text = qualification,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(28.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = email,
                        style = MaterialTheme.typography.titleMedium
                    )

                }

            }

            Spacer(modifier = Modifier.width(24.dp))

            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(220.dp)
                    .background(Color.DarkGray.copy(alpha = 0.4f))
            )

            Spacer(modifier = Modifier.width(32.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Work,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(
                        text = experience,
                        style = MaterialTheme.typography.headlineSmall
                    )

                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.MenuBook,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(
                        text = publications,
                        style = MaterialTheme.typography.headlineSmall
                    )

                }

            }

        }

    }

}