package com.example.onemoretick.models.request

data class DeleteTaskRequest(
    val userId: Int,
    val taskId: Int
)