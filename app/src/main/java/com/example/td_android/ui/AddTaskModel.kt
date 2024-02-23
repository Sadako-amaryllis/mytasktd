@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.td_android.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.compose.ui.platform.LocalContext
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.dateTimePicker
import com.example.td_android.data.Task
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
    private val deadline: String
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
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }
    val selectedDate = remember { mutableStateOf<Date?>(null) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "\uD83D\uDCC5 Add my futur task ",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                OutlinedTextField(
                    value = addTaskModel.title,
                    onValueChange = { addTaskModel.setTitle(it) },
                    label = { Text("Titre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = addTaskModel.description,
                    onValueChange = { addTaskModel.setDescription(it) },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { openDialog.value = true },
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Icon(Icons.Default.DateRange, contentDescription = "Calender")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = selectedDate.value?.let { date ->
                                SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(date)
                            } ?: "Sélectionner une date",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Switch(
                        checked = addTaskModel.isImportant,
                        onCheckedChange = { addTaskModel.setIsImportant(it) },
                        colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary)
                    )
                    Text(
                        text = "Important",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { addTaskModel.createTask() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Created Task")
                }
            }
        }
    }

    if (openDialog.value) {
        MaterialDialog(context).show {
            dateTimePicker(requireFutureDateTime = true) { _, calendar ->
                val selectedDateTime =
                    SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(calendar.time)
                Log.d("SelectedDateTime", selectedDateTime)

                addTaskModel.setDeadline(selectedDateTime)
                selectedDate.value = calendar.time
                openDialog.value = false
            }
        }
    }
}