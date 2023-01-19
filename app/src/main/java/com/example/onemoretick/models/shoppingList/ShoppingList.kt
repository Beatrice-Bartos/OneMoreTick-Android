package com.example.onemoretick.models.shoppingList

import com.example.onemoretick.models.itemList.ItemList
import java.time.LocalDate

data class ShoppingList(var id: Int, var name: String, var itemLists: List<ItemList>)
