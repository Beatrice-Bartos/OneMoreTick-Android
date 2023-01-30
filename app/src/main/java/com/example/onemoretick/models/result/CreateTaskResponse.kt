package com.example.onemoretick.models.result

import java.time.LocalDate

data class CreateTaskResponse(
    val id: Int,
    val title: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val isDone: Int,
    val isCategory: Int,
    val idUser: Int
)