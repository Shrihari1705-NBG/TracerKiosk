
package com.tracer.kiosk.presentation.components.sidebar

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tracer.kiosk.R
import com.tracer.kiosk.presentation.navigation.Screen

private data class LanguageOption(
    val code: String,
    val label: String
)

private val languageOptions = listOf(
    LanguageOption("en", "English"),
    LanguageOption("kn", "ಕನ್ನಡ"),
    LanguageOption("hi", "हिन्दी")
)

@Composable
fun Sidebar(
    selectedRoute: String,
    onHomeClick: () -> Unit,
    onNavigateClick: () -> Unit,
    onFacultyClick: () -> Unit,
    onDepartmentClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    var languageMenuExpanded by remember { mutableStateOf(false) }

    val currentLanguageTag =
        AppCompatDelegate.getApplicationLocales()
            .toLanguageTags()
            .substringBefore("-")
            .ifBlank { "en" }

    val selectedLanguage = languageOptions.firstOrNull {
        it.code == currentLanguageTag
    } ?: languageOptions.first()

    Box(
        modifier = Modifier
            .width(240.dp)
            .fillMaxHeight()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF071B4D),
                        Color(0xFF123C82)
                    )
                )
            )
    ) {
        if (selectedRoute != Screen.Navigation.route) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 24.dp)
            ) {
                OutlinedButton(
                    onClick = { languageMenuExpanded = true }
                ) {
                    Text(
                        text = selectedLanguage.label,
                        color = Color.White
                    )
                }

                DropdownMenu(
                    expanded = languageMenuExpanded,
                    onDismissRequest = {
                        languageMenuExpanded = false
                    }
                ) {
                    languageOptions.forEach { language ->
                        DropdownMenuItem(
                            text = { Text(language.label) },
                            onClick = {
                                languageMenuExpanded = false
                                AppCompatDelegate.setApplicationLocales(
                                    LocaleListCompat.forLanguageTags(
                                        language.code
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SidebarItem(
                title = stringResource(R.string.sidebar_home),
                selected = selectedRoute == Screen.Home.route,
                onClick = onHomeClick
            )

            SidebarItem(
                title = stringResource(R.string.sidebar_navigate),
                selected = selectedRoute == Screen.Navigation.route,
                onClick = onNavigateClick
            )

            SidebarItem(
                title = stringResource(R.string.sidebar_faculty_staff),
                selected = selectedRoute == Screen.Faculty.route,
                onClick = onFacultyClick
            )

            SidebarItem(
                title = stringResource(R.string.sidebar_department),
                selected = selectedRoute == Screen.Department.route,
                onClick = onDepartmentClick
            )

            SidebarItem(
                title = stringResource(R.string.sidebar_about_tracer),
                selected = selectedRoute == Screen.About.route,
                onClick = onAboutClick
            )
        }
    }
}
