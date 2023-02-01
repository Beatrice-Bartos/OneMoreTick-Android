package com.example.onemoretick.usecase

import com.example.onemoretick.models.base.UseCase
import com.example.onemoretick.models.request.CreateTaskRequest
import com.example.onemoretick.models.result.TaskResponse
import com.example.onemoretick.networking.RestClient
import kotlinx.coroutines.Dispatchers

class CreateTaskUseCase : UseCase<CreateTaskRequest, TaskResponse>(Dispatchers.IO) {
    private val restClient = RestClient.INSTANCE

    override suspend fun execute(params: CreateTaskRequest): TaskResponse {
        return restClient.createTask(params.idUser, params)
    }
}


