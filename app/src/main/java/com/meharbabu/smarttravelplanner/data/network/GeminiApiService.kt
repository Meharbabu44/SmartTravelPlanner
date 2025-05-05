package com.meharbabu.smarttravelplanner.data.network

import com.meharbabu.smarttravelplanner.domain.model.ItineraryResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GeminiApiService {
     @Headers("Content-Type: application/json")
     @POST("v1/models/gemini-1.5-pro:generateContent")
     suspend fun generateItinerary(@Query("key") apiKey: String, @Body body: GeminiAIRequest): ItineraryResponse
}