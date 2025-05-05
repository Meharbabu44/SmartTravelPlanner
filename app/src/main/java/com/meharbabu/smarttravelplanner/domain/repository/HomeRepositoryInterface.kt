package com.meharbabu.smarttravelplanner.domain.repository

import com.meharbabu.smarttravelplanner.data.network.GeminiAIRequest
import com.meharbabu.smarttravelplanner.domain.model.ItineraryResponse

interface HomeRepositoryInterface {
    suspend fun generateItinerary(request: GeminiAIRequest): ItineraryResponse
}
