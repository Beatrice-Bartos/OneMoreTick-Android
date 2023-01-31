package com.example.onemoretick.models.shoppingList

import android.os.Parcelable
import com.example.onemoretick.models.itemList.ItemList
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ShoppingList(var id: Int, var name: String, var itemLists: List<ItemList>):Parcelable
