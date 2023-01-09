package com.example.onemoretick.helpers

import android.text.TextUtils
import android.util.Patterns

object UtilValidators {
    fun isValidEmail(email: String?): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return !TextUtils.isEmpty(password) && password.length >= 6
    }

    fun isNotEmptyInput(input: String?): Boolean {
        return !TextUtils.isEmpty(input)
    }
}
