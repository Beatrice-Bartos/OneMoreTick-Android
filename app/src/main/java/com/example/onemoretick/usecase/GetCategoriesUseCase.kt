package com.example.onemoretick.usecase

import com.example.onemoretick.models.base.NoParams
import com.example.onemoretick.models.base.UseCase
import com.example.onemoretick.models.result.CategoryResponse
import com.example.onemoretick.networking.RestClient
import kotlinx.coroutines.Dispatchers

class GetCategoriesUseCase : UseCase<NoParams?, List<CategoryResponse>>(Dispatchers.IO) {
    private val restClient = RestClient.INSTANCE
    override suspend fun execute(params: NoParams?): List<CategoryResponse> {
        return restClient.getCategories()
    }
}