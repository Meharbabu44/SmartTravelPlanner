package com.meharbabu.smarttravelplanner.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.meharbabu.smarttravelplanner.ui.screens.home.HomeScreen
import com.meharbabu.smarttravelplanner.ui.screens.home.HomeViewModel
import com.meharbabu.smarttravelplanner.ui.screens.itinerary.ItineraryScreen
import com.meharbabu.smarttravelplanner.utils.ItineraryParser

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController, viewModel)
        }
        composable(Screen.Itinerary.route) {
            val uiState = viewModel.uiState.collectAsState().value
            ItineraryScreen(ItineraryParser.parse(uiState.itinerary))
        }
    }
}
