package com.example.td_android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
    }
}

@Composable
fun TaskForm(
    addTaskModel: AddTaskModel,
    modifier: Modifier = Modifier
) {
    Column(modifier) {

        Button(
            onClick = { addTaskModel.createTask() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Create task")
        }
    }
}