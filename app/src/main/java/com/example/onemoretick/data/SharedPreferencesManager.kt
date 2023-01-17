package com.example.onemoretick.data

import android.content.Context
import com.example.onemoretick.models.user.UserData

class SharedPreferencesManager private constructor(private val domainContext: Context) {

    val getLoggedInToken: Boolean
        get() {
            val sharedPreferences = domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString("Token", null) != null
        }

    val userAppData: UserData
        get() {
            val sharedPreferences = domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
            return UserData(
                sharedPreferences.getString("Email", "") ?: "",
                sharedPreferences.getString("Password", "") ?: ""
                //sharedPreferences.getString("Token", "") ?: ""
            )
        }

    fun saveUserCredentials(user: UserData) {
        val sharedPreferences = domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("Email", user.email)
        editor.putString("Password", user.password)

        editor.apply()
    }

//    fun saveUserDataToken(userToken: VerifyTokenRequest) {
//        val sharedPreferences = domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//
//        editor.putString("Token", userToken.token)
//
//        editor.apply()
//    }

    fun clearSharedPreferences() {
        val sharedPreferences = domainContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val SHARED_PREFERENCE_NAME = "mainPrefs"
        var domainPreferenceInstance: SharedPreferencesManager? = null

        @Synchronized
        fun getInstance(domainContext: Context): SharedPreferencesManager {
            if (domainPreferenceInstance == null) {
                domainPreferenceInstance = SharedPreferencesManager(domainContext)
            }
            return domainPreferenceInstance as SharedPreferencesManager
        }
    }
}
