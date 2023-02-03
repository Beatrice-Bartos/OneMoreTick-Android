package com.example.onemoretick.usecase

import com.example.onemoretick.models.base.UseCase
import com.example.onemoretick.models.request.LoginUserRequest
import com.example.onemoretick.models.result.LoginUserResponse
import com.example.onemoretick.networking.RestClient
import kotlinx.coroutines.Dispatchers

class LoginUserUseCase : UseCase<LoginUserRequest, LoginUserResponse>(Dispatchers.IO) {
    private val restClient = RestClient.INSTANCE

    override suspend fun execute(params: LoginUserRequest) :LoginUserResponse {
        val response = restClient.loginUser(params)
//        val tokenResponse = response.body().toString()
//        val tokenValue = JSONObject(tokenResponse)
//        val authToken = VerifyTokenRequest(tokenValue["token"].toString())
//        Log.i("Token at login", authToken.token)
        if (!response.isSuccessful) {
            throw IllegalStateException("Bad Credentials")
        }
        return response.body()!!
        //SharedPreferencesManager.domainPreferenceInstance!!.saveUserDataToken(authToken)
    }
}
