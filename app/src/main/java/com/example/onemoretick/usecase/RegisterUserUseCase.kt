package com.example.onemoretick.usecase

import com.example.onemoretick.models.base.UseCase
import com.example.onemoretick.models.request.RegisterUserRequest
import com.example.onemoretick.networking.RestClient
import kotlinx.coroutines.Dispatchers

class RegisterUserUseCase : UseCase<RegisterUserRequest, Unit>(Dispatchers.IO) {
    private val restClient = RestClient.INSTANCE

    override suspend fun execute(params: RegisterUserRequest) {
        val response = restClient.registerUser(params)
//        val tokenResponse = response.body().toString()
//        val tokenValue = JSONObject(tokenResponse)
//        val authToken = VerifyTokenRequest(tokenValue["token"].toString())
//        Log.i("Token at login", authToken.token)
        if (!response.isSuccessful) {
            throw IllegalStateException("Bad Credentials")
        }
        //SharedPreferencesManager.domainPreferenceInstance!!.saveUserDataToken(authToken)
    }
}
