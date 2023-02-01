package com.example.onemoretick.interfaces

import com.example.onemoretick.models.category.Category
import com.example.onemoretick.models.task.Task

public interface OnItemClick {
    fun categoryItemClick(category: Category?)
    fun taskItemClick(task: Task?)
}