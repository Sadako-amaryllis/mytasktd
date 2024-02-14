package com.example.td_android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val score = remember { mutableStateOf(0) }

            NavHost(navController, startDestination = "home") {
                composable("home") { HomeScreen(navController) }
                composable("quiz") { QuizScreen(navController, score) }
                composable("result") { ResultScreen(navController, score.value) }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column {
        Text(
            text = "Welcome to the Quiz App!",
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = { navController.navigate("quiz") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Start Quiz")
        }
    }
}
