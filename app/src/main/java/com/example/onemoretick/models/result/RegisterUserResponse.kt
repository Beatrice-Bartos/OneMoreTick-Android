package com.example.onemoretick.models.result

import com.example.onemoretick.models.shoppingList.ShoppingList
import com.example.onemoretick.models.task.Task
import com.google.gson.annotations.SerializedName

data class RegisterUserResponse(
    val id: Int,
    val email: String,
    val password: String,
    val tasks: List<Task>,
    val shoppingLists: List<ShoppingList>
)
