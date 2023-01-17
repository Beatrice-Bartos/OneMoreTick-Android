package com.example.onemoretick.extensions

import android.content.Context
import android.widget.Toast

fun Context.toastIt(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
