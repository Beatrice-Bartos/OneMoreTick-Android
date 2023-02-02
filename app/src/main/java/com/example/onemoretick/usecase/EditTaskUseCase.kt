package com.example.onemoretick.usecase

import com.example.onemoretick.models.base.UseCase
import com.example.onemoretick.models.request.EditTaskRequest
import com.example.onemoretick.models.result.TaskResponse
import com.example.onemoretick.networking.RestClient
import kotlinx.coroutines.Dispatchers

class EditTaskUseCase : UseCase<EditTaskRequest, TaskResponse>(Dispatchers.IO) {
    private val restClient = RestClient.INSTANCE

    override suspend fun execute(params: EditTaskRequest): TaskResponse {
        return restClient.updateTask(params.idUser, params)
    }
}


