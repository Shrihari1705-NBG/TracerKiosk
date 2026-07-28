package com.tracer.kiosk.presentation.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen("splash")
    data object Home : Screen("home")

    data object Search : Screen("search")

    data object Directory : Screen("directory")

    data object Map : Screen("map")

    data object Information : Screen("information")
}