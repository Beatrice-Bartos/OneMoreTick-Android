package com.example.onemoretick.networking

import com.example.onemoretick.models.request.*
import com.example.onemoretick.models.result.*
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @POST("login")
    suspend fun loginUser(@Body userToLogin: LoginUserRequest): Response<LoginUserResponse>

    @POST("register")
    suspend fun registerUser(@Body userToRegister: RegisterUserRequest): Response<LoginUserResponse>

    @PUT("change_pass")
    suspend fun updatePassword(@Body updatePassword: CreateNewPasswordRequest): Response<ChangePassUserResponse>

    @POST("tasks/{userId}")
    suspend fun createTask(
        @Path("userId") userId: Int,
        @Body createTaskRequest: CreateTaskRequest
    ): TaskResponse

    @PUT("tasks/{userId}")
    suspend fun updateTask(
        @Path("userId") userId: Int,
        @Body editTaskRequest: EditTaskRequest
    ): TaskResponse

    @DELETE("tasks/{userId}/{taskId}")
    suspend fun deleteTask(
        @Path("userId") userId: Int,
        @Path("taskId") taskId: Int
    ): TaskResponse

    @GET("tasks/{userId}")
    suspend fun getTasksByUserId(@Path("userId") userId: Int): List<TaskResponse>

    @GET("categories")
    suspend fun getCategories(): List<CategoryResponse>

    companion object {
//                const val BASE_URL = "http://192.168.1.175:8080/"
                const val BASE_URL = "http://192.168.1.131:8080/"
//        const val BASE_URL = "http://192.168.1.4:8080/"
    }
}
