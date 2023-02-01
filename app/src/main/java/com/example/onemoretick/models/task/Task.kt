package com.example.onemoretick.models.task

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Task(
    var id: Int,
    var title: String,
    var description: String,
    var startDate: LocalDate,
    var endDate: LocalDate,
    var isDone: Int,
    val isCategory: Int,
    val idUser: Int
): Parcelable
