package com.tracer.kiosk.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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

    var navigationDestination by remember {
        mutableStateOf<Destination?>(null)
    }

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
                tracerBotEngine = tracerBotEngine,

                onNavigateToFaculty = { faculty ->

                    navigationDestination =
                        findFacultyDestination(faculty)

                    navController.navigate(
                        Screen.Navigation.route
                    )
                }
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
                tracerBotEngine = tracerBotEngine,

                onNavigateToFaculty = { faculty ->

                    navigationDestination =
                        findFacultyDestination(faculty)

                    navController.navigate(
                        Screen.Navigation.route
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
                tracerBotEngine = tracerBotEngine,

                onNavigateToFaculty = { faculty ->

                    navigationDestination =
                        findFacultyDestination(faculty)

                    navController.navigate(
                        Screen.Navigation.route
                    )
                }
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
                tracerBotEngine = tracerBotEngine,

                onNavigateToFaculty = { faculty ->

                    navigationDestination =
                        findFacultyDestination(faculty)

                    navController.navigate(
                        Screen.Navigation.route
                    )
                }
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


/**
 * Places the existing screen underneath the
 * floating TracerBot overlay.
 *
 * The screen itself remains unchanged.
 */
@Composable
private fun BoxWithTracerBot(
    tracerBotEngine: TracerBotEngine,
    onNavigateToFaculty: (Faculty) -> Unit,
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

            onNavigateToFaculty = onNavigateToFaculty,

            modifier = Modifier.fillMaxSize()
        )
    }
}