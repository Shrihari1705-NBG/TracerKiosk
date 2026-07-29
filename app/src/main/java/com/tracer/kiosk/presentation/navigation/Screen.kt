package com.tracer.kiosk.presentation.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash")
    object Home : Screen("home")

    object Navigation : Screen("navigation")

    object Faculty : Screen("faculty")

    object Department : Screen("department")
    object About : Screen("about")

}