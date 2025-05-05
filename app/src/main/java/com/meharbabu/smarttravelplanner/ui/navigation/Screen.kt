package com.meharbabu.smarttravelplanner.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Itinerary : Screen("itinerary_screen")
}
