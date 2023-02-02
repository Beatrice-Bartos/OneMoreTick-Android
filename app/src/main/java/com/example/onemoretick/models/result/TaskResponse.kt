package com.example.onemoretick.models.result

data class TaskResponse(
    val id: Int,
    val title: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val isDone: Int,
    val idCategory: Int,
    val idUser: Int
)