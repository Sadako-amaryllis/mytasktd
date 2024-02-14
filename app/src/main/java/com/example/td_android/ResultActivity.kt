package com.example.td_android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ResultScreen(navController: NavController, finalScore: Int) {
    Column {
        Text(
            text = "Result Screen",
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Final Score: $finalScore",
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Back to Home")
        }
    }
}



