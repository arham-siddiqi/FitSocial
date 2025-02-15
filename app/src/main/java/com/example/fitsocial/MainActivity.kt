package com.example.fitsocial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitSocialApp()
        }
    }
}

@Composable
fun FitSocialApp() {
    MaterialTheme {
        FitnessApp()
    }
}

@Composable
fun FitnessApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("profile") { ProfileScreen() }
        composable("workout") { WorkoutTracker() }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    var workoutCount by remember { mutableStateOf(0) } // State to track workouts

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to FitSocial!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Workouts Completed: $workoutCount",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = { workoutCount++ }) {
            Text("Start Workout")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("workout") }) {
            Text("Go to Workout Tracker")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Your Friends:", style = MaterialTheme.typography.titleMedium)
        FriendList()
    }
}

@Composable
fun FriendList() {
    val friends = listOf("Alice", "Bob", "Charlie", "Diana")

    LazyColumn {
        items(friends) { friend ->
            Text(
                text = friend,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun WorkoutTracker() {
    var time by remember { mutableStateOf(0) }
    val timer = remember { Timer() }

    LaunchedEffect(Unit) {
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                time++
            }
        }, 1000, 1000)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Time Elapsed: $time seconds", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { timer.cancel() }) {
            Text("Stop Workout")
        }
    }
}

// Preview Functions
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    FitSocialApp()
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewWorkoutTracker() {
    WorkoutTracker()
}