package com.meharbabu.smarttravelplanner.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meharbabu.smarttravelplanner.data.network.GeminiAIRequest
import com.meharbabu.smarttravelplanner.domain.repository.HomeRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepositoryInterface
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun generateItinerary(destination: String, startDate: String, endDate: String, travelStyle: String) {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)
            try {
                val requestBody = GeminiAIRequest(
                    contents = listOf(
                        GeminiAIRequest.Content(
                            parts = listOf(
                                GeminiAIRequest.Part(
                                    text = "Plan a travel itinerary for $destination from $startDate to $endDate focusing on $travelStyle travel style."
                                )
                            )
                        )
                    )
                )
                val response = repository.generateItinerary(requestBody)
                val itinerary = response.candidates[0].content.parts[0].text
                _uiState.value = HomeUiState(itinerary = itinerary)

            } catch (e: Exception) {
                _uiState.value = HomeUiState(error = e.localizedMessage ?: "Unknown Error")
            }
        }
    }
}
