package com.example.onemoretick.usecase

import com.example.onemoretick.models.base.UseCase
import com.example.onemoretick.models.request.RegisterUserRequest
import com.example.onemoretick.models.result.LoginUserResponse
import com.example.onemoretick.models.result.RegisterUserResponse
import com.example.onemoretick.networking.RestClient
import kotlinx.coroutines.Dispatchers

class RegisterUserUseCase : UseCase<RegisterUserRequest, LoginUserResponse>(Dispatchers.IO) {
    private val restClient = RestClient.INSTANCE

    override suspend fun execute(params: RegisterUserRequest) : LoginUserResponse{
        val response = restClient.registerUser(params)
        if (!response.isSuccessful) {
            throw IllegalStateException("Bad Credentials")
        }
        return response.body()!!
    }
}
