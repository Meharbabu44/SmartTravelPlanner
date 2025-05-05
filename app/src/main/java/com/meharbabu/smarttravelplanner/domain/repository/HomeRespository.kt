package com.meharbabu.smarttravelplanner.domain.repository

import com.meharbabu.smarttravelplanner.data.network.GeminiApiService
import com.meharbabu.smarttravelplanner.data.network.GeminiAIRequest
import com.meharbabu.smarttravelplanner.domain.model.ItineraryResponse

class HomeRepository(
    private val apiService: GeminiApiService
) : HomeRepositoryInterface {
    override suspend fun generateItinerary(request: GeminiAIRequest): ItineraryResponse {
        return apiService.generateItinerary("REPLACE_YOUR_API_KEY", request)
    }
}
