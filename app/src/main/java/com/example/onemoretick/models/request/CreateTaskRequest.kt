package com.example.onemoretick.models.request

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

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
    @SerializedName("startDate")
    val idUser: Int
)