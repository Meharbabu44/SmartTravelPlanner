package com.meharbabu.smarttravelplanner.domain.model

data class ItineraryResponse(
    val candidates: List<Candidate>
) {
    data class Candidate(
        val content: Content,
        val finishReason: String,
        val avgLogprobs: Double
    )

    data class Content(
        val parts: List<Part>,
        val role: String
    )

    data class Part(
        val text: String
    )
}
