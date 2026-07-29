package com.tracer.kiosk.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tracer.kiosk.presentation.feature.about.AboutScreen
import com.tracer.kiosk.presentation.feature.home.HomeScreen
import com.tracer.kiosk.presentation.feature.splash.SplashScreen
import com.tracer.kiosk.presentation.feature.faculty.FacultyScreen
import com.tracer.kiosk.presentation.feature.department.DepartmentScreen

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

    }

}