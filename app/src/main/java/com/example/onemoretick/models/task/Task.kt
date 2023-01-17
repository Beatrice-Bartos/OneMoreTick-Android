package com.example.onemoretick.models.task

data class Task(
    var title: String,
    var description: String,
    var startDate: String,
    var endDate: String,
    var isDone: Int
)
