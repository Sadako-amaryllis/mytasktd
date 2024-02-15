@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.td_android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel


class AddTaskModel : ViewModel() {

    private val _title = mutableStateOf("")
    val title: String
        get() = _title.value

    private val _description = mutableStateOf("")
    val description: String
        get() = _description.value

    private val _isImportant = mutableStateOf(false)
    val isImportant: Boolean
        get() = _isImportant.value

    private val _deadline = mutableStateOf("")
    val deadline: String
        get() = _deadline.value

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    fun setIsImportant(isImportant: Boolean) {
        _isImportant.value = isImportant
    }

    fun setDeadline(deadline: String) {
        _deadline.value = deadline
    }

    fun createTask() {
        val task = Task(
            title = title,
            description = description,
            deadline = deadline,
            isImportant = isImportant
        )
        println("New Task Made: $task")
    }
}


@Composable
fun TaskForm(
    addTaskModel: AddTaskModel,
    modifier: Modifier = Modifier

) {

    Column(modifier) {
        OutlinedTextField(
            value = addTaskModel.title,
            onValueChange = { addTaskModel.setTitle(it) },
            label = { Text("Titre") },
        )
        OutlinedTextField(
            value = addTaskModel.description,
            onValueChange = { addTaskModel.setDescription(it) },
            label = { Text("Description") },
        )
        OutlinedTextField(
            value = addTaskModel.deadline,
            onValueChange = { addTaskModel.setDeadline(it) },
            label = { Text("Date limite") },
        )
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Checkbox(
                checked = addTaskModel.isImportant,
                onCheckedChange = { addTaskModel.setIsImportant(it) },
            )
            Text(
                text = "Important",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Button(
            onClick = { addTaskModel.createTask() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Créer une tâche")
        }
    }
}

data class Task(
    val title: String,
    val description: String,
    val deadline: String,
    val isImportant: Boolean
) {
    override fun toString(): String {
        return "Task(title='$title', description='$description', deadline='$deadline', isImportant=$isImportant)"
    }
}