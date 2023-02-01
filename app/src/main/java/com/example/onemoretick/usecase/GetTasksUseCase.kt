package com.example.onemoretick.usecase

import com.example.onemoretick.models.base.UseCase
import com.example.onemoretick.models.result.TaskResponse
import com.example.onemoretick.networking.RestClient
import kotlinx.coroutines.Dispatchers

class GetTasksUseCase : UseCase<Int, List<TaskResponse>>(Dispatchers.IO) {
    private val restClient = RestClient.INSTANCE
    override suspend fun execute(params: Int): List<TaskResponse> {
        return restClient.getTasksByUserId(params)
    }
}