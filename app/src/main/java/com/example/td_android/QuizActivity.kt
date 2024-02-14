package com.example.td_android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun QuizScreen(navController: NavController, score: MutableState<Int>) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    val questions = listOf(
        "What is the capital of France?" to listOf("Paris", "London", "Berlin", "Rome"),
        "What is the largest mammal?" to listOf("Elephant", "Whale", "Giraffe", "Hippopotamus"),
        "Who painted the Mona Lisa?" to listOf("Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Michelangelo"),
        "What is the chemical symbol for water?" to listOf("H2O", "CO2", "NaCl", "O2"),
        "What is the tallest mountain in the world?" to listOf("Mount Everest", "K2", "Kangchenjunga", "Lhotse")
    )

    Column {
        val (question, answers) = questions[currentQuestionIndex]

        Text(
            text = question,
            modifier = Modifier.padding(16.dp)
        )

        // Answers in a two-column grid
        answers.forEach { answer ->
            AnswerButton(answer) {
                if (answer == answers.first()) {
                    score.value++
                }
                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++
                } else {
                    navController.navigate("result")
                }
            }
        }

        Text(
            text = "Score: ${score.value}",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun AnswerButton(text: String, onClickAction: () -> Unit) {
    Button(
        onClick = onClickAction,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text)
    }
}
