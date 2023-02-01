package com.example.onemoretick.interfaces

import com.example.onemoretick.models.category.Category
import com.example.onemoretick.models.result.TaskResponse
import com.example.onemoretick.models.task.Task

interface OnTaskItemClick {
    fun taskItemClick(task: TaskResponse?)
}