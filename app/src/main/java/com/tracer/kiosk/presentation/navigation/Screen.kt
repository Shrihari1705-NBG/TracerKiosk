package com.tracer.kiosk.presentation.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash")
    object Home : Screen("home")
    object About : Screen("about")
    object Faculty : Screen("faculty")

}