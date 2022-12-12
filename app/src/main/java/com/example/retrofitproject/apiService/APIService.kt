package com.example.retrofitproject.apiService

import com.example.retrofitproject.LoginTokenResponse
import com.example.retrofitproject.OtpRequest
import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Query as Query

interface APIService {


    @GET("api/appVersion/salesdata")
    suspend fun getAppversion(): Response<ArrayList<JsonElement>>

    @FormUrlEncoded
    @POST("/api/login")
    suspend fun getToken(
        @Field("email") username: String?,
        @Field("password") password: String?
    ): Response<LoginTokenResponse>

    @POST("/api/SalesAppLogin/ViaOtp")
    suspend fun getOtp(@Body otpRequest: OtpRequest): Response<JsonElement>


   @GET("/api/appVersion/SupplierApp")
   suspend fun getVersion():Response<JsonElement>

    @GET("/api/Suppliers/GenotpForSupplierLogin")
    suspend fun getOtp1(@Query("Mobile") Mobile:String):Response<JsonElement>








}