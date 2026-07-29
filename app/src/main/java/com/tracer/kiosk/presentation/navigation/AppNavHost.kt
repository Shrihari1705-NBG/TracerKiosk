package com.tracer.kiosk.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tracer.kiosk.presentation.feature.home.HomeScreen
import com.tracer.kiosk.presentation.feature.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(
            route = Screen.Splash.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(350)
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(350)
                )
            }
        ) {

            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )

        }

        composable(
            route = Screen.Home.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(1000)
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(1000)
                )
            }
        ) {

            HomeScreen()

        }

    }
}