package com.meharbabu.smarttravelplanner.ui.screens.itinerary

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ItineraryScreen(itinerary: String?) {
    var isLoading by remember { mutableStateOf(itinerary == null) }

    LaunchedEffect(itinerary) {
        if (itinerary != null) {
            isLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (isLoading) {
            // Show loading indicator while waiting for the response
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            // Display the raw itinerary text with formatting
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Text(
                        text = buildAnnotatedString {
                            appendFormattedText(itinerary ?: "")
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
private fun AnnotatedString.Builder.appendFormattedText(input: String) {
    val lines = input.split("\n")
    lines.forEachIndexed { index, line ->
        val formattedLine = if (line.startsWith("#")) line.removePrefix("#").trim() else line

        val regex = Regex("\\*\\*(.+?):\\*\\*") // Matches **something:**
        val matchResult = regex.find(formattedLine)

        if (matchResult != null) {
            // Handle text between ** and ending with :**
            val boldText = matchResult.groupValues[1] + ":"
            pushStyle(MaterialTheme.typography.bodyMedium.toSpanStyle().copy(fontWeight = FontWeight.Bold))
            append(boldText)
            pop()
            append(formattedLine.replace(matchResult.value, "").trim())
        } else if (index == 0 || formattedLine.startsWith("**") && formattedLine.endsWith("**")) {
            // Make the first line or double-starred text bold
            pushStyle(MaterialTheme.typography.bodyMedium.toSpanStyle().copy(fontWeight = FontWeight.Bold))
            append(formattedLine.trim('*'))
            pop()
        } else {
            append(formattedLine)
        }
        append("\n") // Add line break
    }
}