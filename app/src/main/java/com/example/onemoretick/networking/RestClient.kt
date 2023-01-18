package com.example.onemoretick.networking

import com.example.onemoretick.models.request.CreateNewPasswordRequest
import com.example.onemoretick.models.request.LoginUserRequest
import com.example.onemoretick.models.request.RegisterUserRequest
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RestClient {

    suspend fun loginUser(request: LoginUserRequest): Response<Any>
    suspend fun registerUser(request: RegisterUserRequest): Response<Any>

    //    suspend fun verifyToken(userToken: VerifyTokenRequest): Response<Any>
//    suspend fun sendEmailResetPassword(username: String): ResetPasswordResponse
    suspend fun updatePassword(createNewPasswordRequest: CreateNewPasswordRequest): Response<Any>
//    suspend fun getProducts(): List<ProductResponse>
//    suspend fun getProductsById(productId: String): ProductResponse
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
    ): Response<Any> {
        return api.loginUser(request)
    }

    override suspend fun registerUser(
        request: RegisterUserRequest,
    ): Response<Any> {
        return api.registerUser(request)
    }

    override suspend fun updatePassword(createNewPasswordRequest: CreateNewPasswordRequest): Response<Any> {
        return api.updatePassword(createNewPasswordRequest)
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
//    override suspend fun getProductsById(productId: String): ProductResponse {
//        return api.getProductById(productId)
//    }
//
//    override suspend fun getProductAndSuggestionsById(productId: String): ProductAndSuggestionsResponse {
//        return api.getProductAndSuggestionsById(productId)
//    }

    init {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build()
        retrofit = Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        api = retrofit.create(ApiInterface::class.java)
    }
}
