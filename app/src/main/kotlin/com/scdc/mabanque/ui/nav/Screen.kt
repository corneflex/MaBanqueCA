package com.scdc.mabanque.ui.nav

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Account : Screen("account")
    object Simulation : Screen("simulation")
    object Play : Screen("play")
}