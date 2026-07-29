package com.tracer.kiosk.presentation.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.tracer.kiosk.presentation.components.idle.IdleContainer
import com.tracer.kiosk.presentation.navigation.AppNavHost
import com.tracer.kiosk.presentation.theme.TracerKioskTheme

@Composable
fun TracerKioskApp() {

    val navController = rememberNavController()

    TracerKioskTheme {

        IdleContainer {

            AppNavHost(
                navController = navController
            )

        }

    }
}