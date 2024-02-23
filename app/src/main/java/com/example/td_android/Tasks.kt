package com.example.td_android

data class Tasks(
    val id: Int,
    val title: String,
    val description: String,
    val deadline: String,
    val isCompleted: Boolean,
)

data class TaskData(
    val id: Int,
    val attributes: Tasks
)
