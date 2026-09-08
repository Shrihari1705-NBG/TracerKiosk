package com.tracer.kiosk.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.tracer.kiosk.presentation.feature.about.AboutScreen
import com.tracer.kiosk.presentation.feature.home.HomeScreen
import com.tracer.kiosk.presentation.feature.splash.SplashScreen
import com.tracer.kiosk.presentation.feature.faculty.FacultyScreen
import com.tracer.kiosk.presentation.feature.department.DepartmentScreen
import com.tracer.kiosk.presentation.feature.navigation.NavigationScreen

import com.tracer.kiosk.presentation.tracerbot.engine.TracerBotEngine
import com.tracer.kiosk.presentation.tracerbot.ui.TracerBotOverlay

@Composable
fun AppNavHost(
    navController: NavHostController,
    tracerBotEngine: TracerBotEngine
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        // =========================================================
        // SPLASH
        // TracerBot hidden
        // =========================================================

        composable(
            route = Screen.Splash.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(300)
                )
            }
        ) {

            SplashScreen(
                onNavigateToHome = {

                    navController.navigate(
                        Screen.Home.route
                    ) {

                        popUpTo(
                            Screen.Splash.route
                        ) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // =========================================================
        // HOME
        // TracerBot enabled
        // =========================================================

        composable(
            route = Screen.Home.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(300)
                )
            }
        ) {

            BoxWithTracerBot(
                tracerBotEngine = tracerBotEngine
            ) {

                HomeScreen(
                    navController = navController
                )
            }
        }

        // =========================================================
        // NAVIGATION
        // TracerBot enabled
        // =========================================================

        composable(
            route = Screen.Navigation.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(300)
                )
            }
        ) {

            BoxWithTracerBot(
                tracerBotEngine = tracerBotEngine
            ) {

                NavigationScreen(
                    navController = navController
                )
            }
        }

        // =========================================================
        // FACULTY
        // TracerBot enabled
        // =========================================================

        composable(
            route = Screen.Faculty.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(300)
                )
            }
        ) {

            BoxWithTracerBot(
                tracerBotEngine = tracerBotEngine
            ) {

                FacultyScreen(
                    navController = navController
                )
            }
        }

        // =========================================================
        // DEPARTMENT
        // TracerBot enabled
        // =========================================================

        composable(
            route = Screen.Department.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(300)
                )
            }
        ) {

            BoxWithTracerBot(
                tracerBotEngine = tracerBotEngine
            ) {

                DepartmentScreen(
                    navController = navController
                )
            }
        }

        // =========================================================
        // ABOUT
        // TracerBot intentionally hidden
        // =========================================================

        composable(
            route = Screen.About.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(300)
                )
            }
        ) {

            AboutScreen(
                navController = navController
            )
        }
    }
}


/**
 * Places the existing screen underneath the
 * floating TracerBot overlay.
 *
 * The screen itself remains unchanged.
 */
@Composable
private fun BoxWithTracerBot(
    tracerBotEngine: TracerBotEngine,
    content: @Composable () -> Unit
) {

    androidx.compose.foundation.layout.Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // ---------------------------------------------------------
        // Existing kiosk screen
        // ---------------------------------------------------------

        content()

        // ---------------------------------------------------------
        // TracerBot floating assistant
        // ---------------------------------------------------------

        TracerBotOverlay(
            tracerBotEngine = tracerBotEngine,
            modifier = Modifier.fillMaxSize()
        )
    }
}