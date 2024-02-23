package com.example.td_android

data class Tasks(
    val id: Int,
    val title: String,
    val description: String,
    val deadline: String,
    val isImportant: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val publishedAt: String
)
