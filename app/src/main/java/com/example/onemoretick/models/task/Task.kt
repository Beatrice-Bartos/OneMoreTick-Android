package com.example.onemoretick.models.task

import java.time.LocalDate

data class Task(
    var id: Int,
    var title: String,
    var description: String,
    var startDate: LocalDate,
    var endDate: LocalDate,
    var isDone: Int
)
