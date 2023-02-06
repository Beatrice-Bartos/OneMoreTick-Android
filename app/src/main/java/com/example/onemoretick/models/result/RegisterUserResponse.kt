package com.example.onemoretick.models.result

import android.os.Parcelable
import com.example.onemoretick.models.task.Task
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterUserResponse(
    val id: Int,
    val email: String,
    val password: String,
): Parcelable

