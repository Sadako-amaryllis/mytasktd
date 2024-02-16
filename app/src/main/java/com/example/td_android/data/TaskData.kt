package com.example.td_android.data

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