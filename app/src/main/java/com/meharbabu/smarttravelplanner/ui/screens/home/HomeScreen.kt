package com.meharbabu.smarttravelplanner.ui.screens.home

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.remember
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.meharbabu.smarttravelplanner.ui.navigation.Screen
import java.util.*

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    // Collect uiState from ViewModel
    val uiState by viewModel.uiState.collectAsState()

    var destination by remember { mutableStateOf(uiState.destination) }
    var startDate by remember { mutableStateOf(uiState.startDate) }
    var endDate by remember { mutableStateOf(uiState.endDate) }
    var travelStyle by remember { mutableStateOf(uiState.travelStyle) }

    val travelStyles = listOf("Adventure Travel","Backpacking","Cultural Travel",
        "Digital Nomad", "Family Travel", "Group Travel", "Luxury Travel","Road Trip",
        "Solo Travel")
    val selectedTravelStyle = remember { mutableStateOf(travelStyle) }

    var isStartDatePickerOpen by remember { mutableStateOf(false) }
    var isEndDatePickerOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                OutlinedTextField(
                    value = destination,
                    onValueChange = { destination = it },
                    label = { Text("Enter your travel destination") },
                    trailingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Start Date Field
                OutlinedTextField(
                    value = startDate,
                    onValueChange = { startDate = it },
                    label = { Text("Start Date") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    trailingIcon = {
                        IconButton(onClick = {
                            isStartDatePickerOpen = true
                        }) {
                            Icon(Icons.Default.CalendarToday, contentDescription = "Pick Date")
                        }
                    }
                )

                // Show DatePicker Dialog for Start Date
                if (isStartDatePickerOpen) {
                    val calendar = Calendar.getInstance()
                    val datePickerDialog = DatePickerDialog(
                        LocalContext.current,
                        { _, year, month, dayOfMonth ->
                            val date = "$dayOfMonth/${month + 1}/$year"
                            startDate = date
                            isStartDatePickerOpen = false
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    datePickerDialog.show()
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = endDate,
                    onValueChange = { endDate = it },
                    label = { Text("End Date") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    trailingIcon = {
                        IconButton(onClick = {
                            isEndDatePickerOpen = true
                        }) {
                            Icon(Icons.Default.CalendarToday, contentDescription = "Pick Date")
                        }
                    }
                )

                // Show DatePicker Dialog for End Date
                if (isEndDatePickerOpen) {
                    val calendar = Calendar.getInstance()
                    val datePickerDialog = DatePickerDialog(
                        LocalContext.current,
                        { _, year, month, dayOfMonth ->
                            val date = "$dayOfMonth/${month + 1}/$year"
                            endDate = date
                            isEndDatePickerOpen = false
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    datePickerDialog.show()
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Travel Style Dropdown
                var expanded by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = selectedTravelStyle.value,
                        onValueChange = { selectedTravelStyle.value = it },
                        label = { Text("Select Travel Style") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = { expanded = !expanded }) {
                                Icon(Icons.Default.Style, contentDescription = "Travel Style")
                            }
                        }
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        travelStyles.forEach { style ->
                            DropdownMenuItem(
                                text = { Text(text = style) },
                                onClick = {
                                    selectedTravelStyle.value = style
                                    expanded = false // Dismiss the dropdown
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Generate Itinerary Button
                Button(
                    onClick = {
                        viewModel.generateItinerary(
                            destination,
                            startDate,
                            endDate,
                            selectedTravelStyle.value
                        )
                        navController.navigate(Screen.Itinerary.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5C5C)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Get Travel Plan", color = Color.White)
                }
            }
        }
    }
}
