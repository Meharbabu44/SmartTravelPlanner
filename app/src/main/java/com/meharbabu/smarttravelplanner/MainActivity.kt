package com.meharbabu.smarttravelplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.meharbabu.smarttravelplanner.data.network.NetworkModule
import com.meharbabu.smarttravelplanner.domain.repository.HomeRepository
import com.meharbabu.smarttravelplanner.ui.navigation.NavGraph
import com.meharbabu.smarttravelplanner.ui.screens.home.HomeViewModel
import com.meharbabu.smarttravelplanner.ui.theme.ItineraryAppTheme

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make the status bar color match the app theme
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = getColor(android.R.color.transparent)


        setContent {
            ItineraryAppTheme  {
                // Set the status bar color to match the app theme
                window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
                Scaffold(
                    topBar = {
                        @OptIn(ExperimentalMaterial3Api::class)
                        TopAppBar(
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors
                                (containerColor = Color(0xFFFF5A5F)),
                            title = { Text("GoTrip", color = Color.White) }
                        )
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()

                        val repository = HomeRepository(NetworkModule.provideRetrofit())
                        val viewModel = HomeViewModel(repository)

                        NavGraph(navController = navController, viewModel = viewModel)
                    }
                }

            }
        }
    }
}