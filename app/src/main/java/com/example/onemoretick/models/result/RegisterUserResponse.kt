package com.example.onemoretick.models.result

import com.example.onemoretick.models.task.Task

data class RegisterUserResponse(
    val id: Int,
    val email: String,
    val password: String,
    val tasks: List<Task>
)
