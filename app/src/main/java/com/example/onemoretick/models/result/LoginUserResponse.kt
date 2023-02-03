package com.example.onemoretick.models.result

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginUserResponse(
    val id: Int,
    val email: String,
    val password: String,
) : Parcelable
