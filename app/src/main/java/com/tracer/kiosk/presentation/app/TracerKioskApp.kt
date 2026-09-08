package com.tracer.kiosk.presentation.app

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.tracer.kiosk.presentation.components.idle.IdleContainer
import com.tracer.kiosk.presentation.navigation.AppNavHost
import com.tracer.kiosk.presentation.theme.TracerKioskTheme
import com.tracer.kiosk.presentation.tracerbot.data.FacultyRepository
import com.tracer.kiosk.presentation.tracerbot.engine.TracerBotEngine

@Composable
fun TracerKioskApp() {

    val navController = rememberNavController()

    TracerKioskTheme {

        val context = androidx.compose.ui.platform.LocalContext.current

        val facultyRepository = remember {
            FacultyRepository(
                context = context.applicationContext
            )
        }

        val tracerBotEngine = remember {
            TracerBotEngine(
                facultyRepository = facultyRepository
            )
        }

        IdleContainer {

            AppNavHost(
                navController = navController,
                tracerBotEngine = tracerBotEngine
            )

        }
    }
}