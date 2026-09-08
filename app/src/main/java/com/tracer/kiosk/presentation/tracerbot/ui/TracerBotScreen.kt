package com.tracer.kiosk.presentation.tracerbot.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tracer.kiosk.R
import com.tracer.kiosk.presentation.tracerbot.model.Faculty
import com.tracer.kiosk.presentation.tracerbot.viewmodel.TracerBotUiState
import com.tracer.kiosk.presentation.tracerbot.viewmodel.TracerBotViewModel

private val TracerNavy = Color(0xFF00183F)
private val TracerBlue = Color(0xFF477ACB)
private val TracerLightBlue = Color(0xFFEAF2FF)
private val TracerBackground = Color(0xFFF6F9FF)
private val TracerText = Color(0xFF071A3D)

/**
 * Main TracerBot screen.
 *
 * This screen is responsible only for displaying the
 * TracerBot interface and communicating with the ViewModel.
 */
@Composable
fun TracerBotScreen(
    viewModel: TracerBotViewModel,
    onClose: () -> Unit = {},
    onNavigateToFaculty: (Faculty) -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsState()

    TracerBotContent(
        uiState = uiState,
        onQueryChanged = viewModel::updateQuery,
        onSubmit = viewModel::submitQuery,

        // ---------------------------------------------------------
        // Close button
        // ---------------------------------------------------------

        onClose = {
            viewModel.closeBot()
            onClose()
        },

        onSuggestionClick = { suggestion ->
            viewModel.updateQuery(suggestion)
            viewModel.submitQuery()
        },

        onFacultyClick = onNavigateToFaculty
    )
}

/**
 * Visual content of the TracerBot screen.
 */
@Composable
private fun TracerBotContent(
    uiState: TracerBotUiState,
    onQueryChanged: (String) -> Unit,
    onSubmit: () -> Unit,
    onClose: () -> Unit,
    onSuggestionClick: (String) -> Unit,
    onFacultyClick: (Faculty) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        TracerBackground,
                        Color.White
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // =====================================================
            // Header
            // =====================================================

            TracerBotHeader(
                onClose = onClose
            )

            // =====================================================
            // Main scrollable content
            // =====================================================

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),

                contentPadding = PaddingValues(
                    horizontal = 32.dp,
                    vertical = 24.dp
                ),

                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                // -------------------------------------------------
                // Welcome
                // -------------------------------------------------

                item {
                    WelcomeSection()
                }

                // -------------------------------------------------
                // Response
                // -------------------------------------------------

                if (uiState.response != null) {

                    item {

                        ResponseCard(
                            message = uiState.response.message
                        )
                    }
                }

                // -------------------------------------------------
                // Error
                // -------------------------------------------------

                if (uiState.errorMessage != null) {

                    item {

                        ErrorCard(
                            message = uiState.errorMessage
                        )
                    }
                }

                // -------------------------------------------------
                // Faculty results
                // -------------------------------------------------

                if (uiState.facultyMatches.isNotEmpty()) {

                    item {

                        Text(
                            text = "Faculty found",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = TracerText
                        )
                    }

                    items(
                        items = uiState.facultyMatches
                    ) { faculty ->

                        FacultyResultCard(
                            faculty = faculty,
                            onClick = {
                                onFacultyClick(faculty)
                            }
                        )
                    }
                }

                // -------------------------------------------------
                // Suggestions
                // -------------------------------------------------

                if (
                    uiState.response == null &&
                    !uiState.isLoading
                ) {

                    item {

                        SuggestionsSection(
                            onSuggestionClick = onSuggestionClick
                        )
                    }
                }

                // -------------------------------------------------
                // Loading
                // -------------------------------------------------

                if (uiState.isLoading) {

                    item {

                        LoadingSection()
                    }
                }
            }

            // =====================================================
            // Query input
            // =====================================================

            QueryInput(
                query = uiState.query,
                isLoading = uiState.isLoading,
                onQueryChanged = onQueryChanged,
                onSubmit = onSubmit
            )
        }
    }
}

/**
 * TracerBot top header.
 */
@Composable
private fun TracerBotHeader(
    onClose: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = TracerNavy,
        shadowElevation = 6.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
                .padding(horizontal = 28.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // =====================================================
            // Bot avatar
            // =====================================================

            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.tracerbot_avatar
                    ),
                    contentDescription = "TracerBot",
                    modifier = Modifier.size(48.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(
                modifier = Modifier.width(18.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "TracerBot",
                    color = Color.White,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Your campus assistant",
                    color = Color.White.copy(alpha = 0.75f),
                    fontSize = 15.sp
                )
            }

            // =====================================================
            // CLOSE BUTTON
            // =====================================================

            IconButton(
                onClick = onClose
            ) {

                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close TracerBot",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

/**
 * Initial greeting section.
 *
 * The avatar is intentionally NOT placed inside a Card,
 * Surface, or clipped container.
 */
@Composable
private fun WelcomeSection() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 28.dp,
                start = 20.dp,
                end = 20.dp
            ),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // =========================================================
        // TracerBot welcome avatar
        //
        // No background container.
        // No clipping.
        // This allows the full avatar artwork to remain visible.
        // =========================================================

        Image(
            painter = painterResource(
                id = R.drawable.tracerbot_avatar
            ),
            contentDescription = "TracerBot",

            modifier = Modifier
                .size(125.dp),

            contentScale = ContentScale.Fit
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Hello! I'm TracerBot",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = TracerText
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = "Ask me about faculty, departments,\nnavigation, or the Tracer Kiosk.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.DarkGray
        )
    }
}

/**
 * Suggested questions.
 */
@Composable
private fun SuggestionsSection(
    onSuggestionClick: (String) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Try asking",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = TracerText
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        SuggestionButton(
            text = "Tell me about a faculty member",
            onClick = {
                onSuggestionClick(
                    "Tell me about a faculty member"
                )
            }
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        SuggestionButton(
            text = "What does a professor teach?",
            onClick = {
                onSuggestionClick(
                    "What does a professor teach?"
                )
            }
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        SuggestionButton(
            text = "What are the research interests of the faculty?",
            onClick = {
                onSuggestionClick(
                    "What are the research interests of the faculty?"
                )
            }
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        SuggestionButton(
            text = "Help me navigate the campus",
            onClick = {
                onSuggestionClick(
                    "Help me navigate the campus"
                )
            }
        )
    }
}

/**
 * Single suggestion button.
 */
@Composable
private fun SuggestionButton(
    text: String,
    onClick: () -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable {
                onClick()
            },
        color = TracerLightBlue,
        shadowElevation = 1.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 17.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = text,
                modifier = Modifier.weight(1f),
                fontSize = 17.sp,
                color = TracerText
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = TracerBlue
            )
        }
    }
}

/**
 * User query input area.
 */
@Composable
private fun QueryInput(
    query: String,
    isLoading: Boolean,
    onQueryChanged: (String) -> Unit,
    onSubmit: () -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .imePadding(),
        color = Color.White,
        shadowElevation = 10.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp,
                    vertical = 18.dp
                ),

            verticalAlignment = Alignment.CenterVertically
        ) {

            // =====================================================
            // Text field
            // =====================================================

            OutlinedTextField(
                value = query,
                onValueChange = onQueryChanged,

                modifier = Modifier.weight(1f),

                enabled = !isLoading,

                singleLine = true,

                placeholder = {
                    Text(
                        text = "Ask TracerBot anything..."
                    )
                },

                shape = RoundedCornerShape(18.dp)
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            // =====================================================
            // Ask button
            // =====================================================

            Button(
                onClick = onSubmit,

                enabled = query.isNotBlank() && !isLoading,

                modifier = Modifier.height(58.dp),

                shape = RoundedCornerShape(18.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = TracerNavy
                )
            ) {

                if (isLoading) {

                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )

                } else {

                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Ask"
                    )

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Text(
                        text = "Ask",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

/**
 * Response displayed by TracerBot.
 */
@Composable
private fun ResponseCard(
    message: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),

            verticalAlignment = Alignment.Top
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(TracerNavy),

                contentAlignment = Alignment.Center
            ) {

                // Keep SmartToy here for now because this is
                // the response/message indicator, not the
                // main TracerBot avatar.

                Icon(
                    imageVector = Icons.Default.SmartToy,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Text(
                text = message,

                modifier = Modifier.weight(1f),

                fontSize = 18.sp,

                color = TracerText,

                lineHeight = 28.sp
            )
        }
    }
}

/**
 * Faculty result card.
 */
@Composable
private fun FacultyResultCard(
    faculty: Faculty,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(TracerLightBlue),

                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = TracerNavy,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = faculty.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TracerText
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = faculty.designation,
                    fontSize = 15.sp,
                    color = Color.DarkGray
                )

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = faculty.department,
                    fontSize = 14.sp,
                    color = TracerBlue
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Open faculty",
                tint = TracerBlue
            )
        }
    }
}

/**
 * Loading state.
 */
@Composable
private fun LoadingSection() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CircularProgressIndicator(
            color = TracerBlue
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "TracerBot is thinking...",
            fontSize = 16.sp,
            color = Color.DarkGray
        )
    }
}

/**
 * Error state.
 */
@Composable
private fun ErrorCard(
    message: String
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(18.dp),

        color = Color(0xFFFFF3F3)
    ) {

        Text(
            text = message,

            modifier = Modifier.padding(20.dp),

            fontSize = 16.sp,

            color = Color(0xFF8B1E1E)
        )
    }
}