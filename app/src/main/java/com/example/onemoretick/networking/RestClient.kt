package com.example.onemoretick.networking

import com.example.onemoretick.models.request.CreateNewPasswordRequest
import com.example.onemoretick.models.request.CreateTaskRequest
import com.example.onemoretick.models.request.LoginUserRequest
import com.example.onemoretick.models.request.RegisterUserRequest
import com.example.onemoretick.models.result.ChangePassUserResponse
import com.example.onemoretick.models.result.TaskResponse
import com.example.onemoretick.models.result.LoginUserResponse
import com.example.onemoretick.models.result.RegisterUserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path

interface RestClient {

    suspend fun loginUser(request: LoginUserRequest): Response<LoginUserResponse>
    suspend fun registerUser(request: RegisterUserRequest): Response<RegisterUserResponse>

    //    suspend fun verifyToken(userToken: VerifyTokenRequest): Response<Any>
//    suspend fun sendEmailResetPassword(username: String): ResetPasswordResponse
    suspend fun updatePassword(createNewPasswordRequest: CreateNewPasswordRequest): Response<ChangePassUserResponse>
    suspend fun getTasksByUserId(userId: Int): List<TaskResponse>

    //    suspend fun getProducts(): List<ProductResponse>
    suspend fun createTask(
        userId: Int,
        createTaskRequest: CreateTaskRequest
    ): TaskResponse
//    suspend fun getProductAndSuggestionsById(productId: String): ProductAndSuggestionsResponse

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
    ): Response<RegisterUserResponse> {
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

    override suspend fun getTasksByUserId(userId: Int): List<TaskResponse> {
        return api.getTasksByUserId(userId)
    }

//
//    override suspend fun verifyToken(userToken: VerifyTokenRequest): Response<Any> {
//        return api.verifyToken(userToken)
//    }
//
//    override suspend fun sendEmailResetPassword(email: String): ResetPasswordResponse {
//        return api.sendEmailResetPassword(email)
//    }
//
//    override suspend fun updatePassword(createNewPasswordRequest: CreateNewPasswordRequest) {
//        return api.updatePassword(createNewPasswordRequest)
//    }
//
//    override suspend fun getProducts(): List<ProductResponse> {
//        return api.getProducts()
//    }
//
//    override suspend fun getProductAndSuggestionsById(productId: String): ProductAndSuggestionsResponse {
//        return api.getProductAndSuggestionsById(productId)
//    }

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
