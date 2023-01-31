package com.example.onemoretick.models.itemList

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ItemList(var id: Int, var name: String):Parcelable
