package com.example.onemoretick.usecase

import com.example.onemoretick.models.base.UseCase
import com.example.onemoretick.models.request.CreateNewPasswordRequest
import com.example.onemoretick.networking.RestClient
import kotlinx.coroutines.Dispatchers

class ChangePasswordUseCase : UseCase<CreateNewPasswordRequest, Unit>(Dispatchers.IO) {
    private val restClient = RestClient.INSTANCE

    override suspend fun execute(params: CreateNewPasswordRequest) {
        val response = restClient.updatePassword(params)
        if (!response.isSuccessful) {
            throw IllegalStateException("Bad Credentials")
        }
    }
}
