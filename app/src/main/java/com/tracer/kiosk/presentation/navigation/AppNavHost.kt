package com.tracer.kiosk.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

import com.tracer.kiosk.presentation.feature.about.AboutScreen
import com.tracer.kiosk.presentation.feature.home.HomeScreen
import com.tracer.kiosk.presentation.feature.splash.SplashScreen
import com.tracer.kiosk.presentation.feature.faculty.FacultyScreen
import com.tracer.kiosk.presentation.feature.department.DepartmentScreen
import com.tracer.kiosk.presentation.feature.navigation.NavigationScreen
import com.tracer.kiosk.presentation.feature.navigation.data.DestinationRepository
import com.tracer.kiosk.presentation.feature.navigation.model.Destination
import com.tracer.kiosk.presentation.tracerbot.engine.TracerBotEngine
import com.tracer.kiosk.presentation.tracerbot.model.Faculty
import com.tracer.kiosk.presentation.tracerbot.ui.TracerBotOverlay

@Composable
fun AppNavHost(
    navController: NavHostController,
    tracerBotEngine: TracerBotEngine
) {

    // =============================================================
    // Pending navigation destination from TracerBot
    // =============================================================

    var navigationDestination by remember {
        mutableStateOf<Destination?>(null)
    }

    var isTracerBotOpen by remember {
        mutableStateOf(false)
    }

    // =============================================================
    // Current application route
    // =============================================================

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute =
        backStackEntry?.destination?.route

    // =============================================================
    // Root container
    //
    // NavHost is placed first.
    // TracerBot is placed afterwards so it stays ABOVE the app.
    // =============================================================

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // =========================================================
        // Application navigation
        // =========================================================

        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route
        ) {

            // =====================================================
            // SPLASH
            // =====================================================

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

            // =====================================================
            // HOME
            // =====================================================

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

                HomeScreen(
                    navController = navController
                )
            }

            // =====================================================
            // NAVIGATION
            // =====================================================

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

                NavigationScreen(
                    navController = navController,
                    initialDestination = navigationDestination,
                    onInitialDestinationConsumed = {
                        navigationDestination = null
                    }
                )
            }

            // =====================================================
            // FACULTY
            // =====================================================

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

                FacultyScreen(
                    navController = navController
                )
            }

            // =====================================================
            // DEPARTMENT
            // =====================================================

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

                DepartmentScreen(
                    navController = navController
                )
            }

            // =====================================================
            // ABOUT
            // TracerBot intentionally hidden
            // =====================================================

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

        // =========================================================
        // GLOBAL TRACERBOT
        //
        // One single TracerBot instance sits above the entire app.
        // =========================================================

        if (
            currentRoute != Screen.Splash.route &&
            currentRoute != Screen.Navigation.route &&
            currentRoute != Screen.About.route
        )  {

            TracerBotOverlay(
                tracerBotEngine = tracerBotEngine,

                isOpen = isTracerBotOpen,

                onOpen = {
                    isTracerBotOpen = true
                },

                onClose = {
                    isTracerBotOpen = false
                },

                onNavigateToFaculty = { faculty ->

                    isTracerBotOpen = false

                    navigationDestination =
                        findFacultyDestination(faculty)

                    navController.navigate(
                        Screen.Navigation.route
                    )
                },

                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


/**
 * Finds the existing navigation destination
 * corresponding to a TracerBot faculty member.
 */
private fun findFacultyDestination(
    faculty: Faculty
): Destination? {

    return DestinationRepository.destinations.firstOrNull { destination ->

        destination.category.name == "FACULTY" &&
                (
                        destination.name.equals(
                            faculty.name,
                            ignoreCase = true
                        ) ||
                                destination.aliases.any { alias ->

                                    faculty.name.contains(
                                        alias,
                                        ignoreCase = true
                                    )
                                }
                        )
    }
}