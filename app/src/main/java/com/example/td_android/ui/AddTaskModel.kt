package com.example.td_android.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.compose.ui.platform.LocalContext
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.dateTimePicker
import java.text.SimpleDateFormat
import java.util.*

class AddTaskModel : ViewModel() {
    var task by mutableStateOf(Task())

    fun createTask() {
        // Implement task creation logic here
    }
}

data class Task(
    var title: String = "",
    var description: String = "",
    var deadline: String = ""
)

@Composable
fun TaskForm(addTaskModel: AddTaskModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }
    val selectedDate = remember { mutableStateOf<Date?>(null) }

    Box(modifier = modifier
        .fillMaxSize()
        .padding(16.dp), contentAlignment = Alignment.Center) {
        Card(shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                InputField(value = addTaskModel.task.title, onValueChange = { addTaskModel.task = addTaskModel.task.copy(title = it) }, label = "Title")
                Spacer(modifier = Modifier.height(16.dp))
                InputField(value = addTaskModel.task.description, onValueChange = { addTaskModel.task = addTaskModel.task.copy(description = it) }, label = "Description")
                DatePickerDialog(openDialog, selectedDate, addTaskModel)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { addTaskModel.createTask() }, modifier = Modifier.fillMaxWidth()) {
                    Text("Create Task")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(value = value, onValueChange = onValueChange, label = { Text(label) }, modifier = Modifier.fillMaxWidth())
}

@Composable
fun DatePickerDialog(openDialog: MutableState<Boolean>, selectedDate: MutableState<Date?>, addTaskModel: AddTaskModel) {
    val context = LocalContext.current
    IconButton(onClick = { openDialog.value = true }) {
        Icon(Icons.Default.DateRange, contentDescription = "Calendar")
    }
    Text(text = selectedDate.value?.let { SimpleDateFormat("MMM. dd yyyy, HH:mm", Locale.getDefault()).format(it) } ?: "Select a date")

    if (openDialog.value) {
        MaterialDialog(context).show {
            dateTimePicker(requireFutureDateTime = true) { _, calendar ->
                val selectedDateTime = SimpleDateFormat("MMM. dd yyyy, HH:mm", Locale.getDefault()).format(calendar.time)
                addTaskModel.task = addTaskModel.task.copy(deadline = selectedDateTime)
                selectedDate.value = calendar.time
                openDialog.value = false
            }
        }
    }
}
