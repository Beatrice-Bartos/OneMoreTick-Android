package com.example.onemoretick.models.request

import com.google.gson.annotations.SerializedName

data class CreateTaskRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("isDone")
    val isDone: Int,
    @SerializedName("idCategory")
    val idCategory: Int,
    @SerializedName("idUser")
    val idUser: Int
)