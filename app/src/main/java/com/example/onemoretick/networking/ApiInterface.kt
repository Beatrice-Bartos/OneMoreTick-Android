package com.example.onemoretick.networking

import com.example.onemoretick.models.request.LoginUserRequest
import com.example.onemoretick.models.request.RegisterUserRequest
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {


    @POST("login")
    suspend fun loginUser(@Body userToLogin: LoginUserRequest): Response<Any>

    @POST("register")
    suspend fun registerUser(@Body userToRegister: RegisterUserRequest): Response<Any>
//
//    @POST("api/v1/reset_pass/requests/{email}")
//    suspend fun sendEmailResetPassword(@Path("email") email: String): ResetPasswordResponse
//
//    @PUT("api/v1/reset_pass/update")
//    suspend fun updatePassword(@Body updatePassword: CreateNewPasswordRequest)
//
//    @POST("api/v1/auth/token")
//    suspend fun verifyToken(@Body userToken: VerifyTokenRequest): Response<Any>
//
//    @GET("api/v1/products")
//    suspend fun getProducts(): List<ProductResponse>
//
//    @GET("api/v1/products/id/{productId}")
//    suspend fun getProductById(@Path("productId") productId: String): ProductResponse
//
//    @GET("api/v1/products/id/{productId}/suggestions+searched_product")
//    suspend fun getProductAndSuggestionsById(@Path("productId") productId: String): ProductAndSuggestionsResponse

    companion object {
        const val BASE_URL = "http://192.168.1.6:8080/"
    }
}
