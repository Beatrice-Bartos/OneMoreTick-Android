package com.example.onemoretick.networking

import com.example.onemoretick.models.request.*
import com.example.onemoretick.models.result.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RestClient {

    suspend fun loginUser(request: LoginUserRequest): Response<LoginUserResponse>
    suspend fun registerUser(request: RegisterUserRequest): Response<LoginUserResponse>
    suspend fun updatePassword(createNewPasswordRequest: CreateNewPasswordRequest): Response<ChangePassUserResponse>
    suspend fun getTasksByUserId(userId: Int): List<TaskResponse>
    suspend fun createTask(
        userId: Int,
        createTaskRequest: CreateTaskRequest
    ): TaskResponse

    suspend fun updateTask(
        userId: Int,
        updateTaskRequest: EditTaskRequest
    ): TaskResponse

    suspend fun deleteTask(
        userId: Int,
        taskId: Int
    ): TaskResponse

    suspend fun getCategories(): List<CategoryResponse>

    companion object {
        val INSTANCE: RestClient = RetrofitRestClient()
    }
}

private class RetrofitRestClient : RestClient {

    private val retrofit: Retrofit
    private val api: ApiInterface

    override suspend fun loginUser(
        request: LoginUserRequest,
    ): Response<LoginUserResponse> {
        return api.loginUser(request)
    }

    override suspend fun registerUser(
        request: RegisterUserRequest,
    ): Response<LoginUserResponse> {
        return api.registerUser(request)
    }

    override suspend fun updatePassword(createNewPasswordRequest: CreateNewPasswordRequest): Response<ChangePassUserResponse> {
        return api.updatePassword(createNewPasswordRequest)
    }

    override suspend fun createTask(
        userId: Int,
        createTaskRequest: CreateTaskRequest
    ): TaskResponse {
        return api.createTask(userId, createTaskRequest)
    }

    override suspend fun updateTask(userId: Int, updateTaskRequest: EditTaskRequest): TaskResponse {
        return api.updateTask(userId, updateTaskRequest)
    }

    override suspend fun deleteTask(userId: Int, taskId: Int): TaskResponse {
        return api.deleteTask(userId, taskId)
    }

    override suspend fun getTasksByUserId(userId: Int): List<TaskResponse> {
        return api.getTasksByUserId(userId)
    }

    override suspend fun getCategories(): List<CategoryResponse> {
        return api.getCategories()
    }

    init {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        api = retrofit.create(ApiInterface::class.java)
    }
}
