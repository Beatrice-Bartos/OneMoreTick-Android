package com.example.onemoretick.usecase

import com.example.onemoretick.models.base.NoParams
import com.example.onemoretick.models.base.UseCase
import com.example.onemoretick.models.request.DeleteTaskRequest
import com.example.onemoretick.models.request.EditTaskRequest
import com.example.onemoretick.models.result.TaskResponse
import com.example.onemoretick.networking.RestClient
import kotlinx.coroutines.Dispatchers

class DeleteTaskUseCase : UseCase<DeleteTaskRequest, TaskResponse>(Dispatchers.IO) {
    private val restClient = RestClient.INSTANCE

    override suspend fun execute(params: DeleteTaskRequest): TaskResponse {
        return restClient.deleteTask(params.userId, params.taskId)
    }
}


