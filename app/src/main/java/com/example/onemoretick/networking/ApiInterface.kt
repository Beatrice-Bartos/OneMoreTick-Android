package com.example.onemoretick.networking

import com.example.onemoretick.models.request.CreateNewPasswordRequest
import com.example.onemoretick.models.request.CreateTaskRequest
import com.example.onemoretick.models.request.LoginUserRequest
import com.example.onemoretick.models.request.RegisterUserRequest
import com.example.onemoretick.models.result.ChangePassUserResponse
import com.example.onemoretick.models.result.TaskResponse
import com.example.onemoretick.models.result.LoginUserResponse
import com.example.onemoretick.models.result.RegisterUserResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {


    @POST("login")
    suspend fun loginUser(@Body userToLogin: LoginUserRequest): Response<LoginUserResponse>

    @POST("register")
    suspend fun registerUser(@Body userToRegister: RegisterUserRequest): Response<RegisterUserResponse>
//
//    @POST("api/v1/reset_pass/requests/{email}")
//    suspend fun sendEmailResetPassword(@Path("email") email: String): ResetPasswordResponse

    @PUT("change_pass")
    suspend fun updatePassword(@Body updatePassword: CreateNewPasswordRequest): Response<ChangePassUserResponse>

    @POST("tasks/{userId}")
    suspend fun createTask(
        @Path("userId") userId: Int,
        @Body createTaskRequest: CreateTaskRequest
    ): TaskResponse

//    @GET("api/v1/products")
//    suspend fun getProducts(): List<ProductResponse>

    @GET("tasks/{userId}")
    suspend fun getTasksByUserId(@Path("userId") userId: Int): List<TaskResponse>

//    @GET("api/v1/products/id/{productId}/suggestions+searched_product")
//    suspend fun getProductAndSuggestionsById(@Path("productId") productId: String): ProductAndSuggestionsResponse

    companion object {
        const val BASE_URL = "http://192.168.1.175:8080/"
    }
}
