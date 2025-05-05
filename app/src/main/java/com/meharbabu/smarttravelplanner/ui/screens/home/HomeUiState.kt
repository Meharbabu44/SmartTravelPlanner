package com.meharbabu.smarttravelplanner.ui.screens.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val itinerary: String = "",
    val error: String = "",
    val destination: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val travelStyle: String = "",
)
