package com.example.retrofitproject.apiService

import com.example.retrofitproject.*
import com.example.retrofitproject.splashScreen.AppVersionModel
import com.example.retrofitproject.splashScreen.employeesResponsModel
import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Query as Query

interface APIService {


    @GET("api/appVersion/salesdata")
    suspend fun getAppversion(): Response<ArrayList<AppVersionModel>>

    @GET("/api/SalesAppLogin")
    suspend fun doLogin(
        @Query("mob") mobile: String,
        @Query("password") password: String,
        @Query("FcmId") fcmId: String,
        @Query("CurrentAPKversion") currentAPKversion: String,
        @Query("PhoneOSversion") phoneOSversion: String,
        @Query("UserDeviceName") userDeviceName: String,
        @Query("DeviceId") deviceId: String,
        @Query("IMEI") iMEI: String
    ): Response<AuthResponse>



    @FormUrlEncoded
    @POST("/token")
    suspend fun getToken(
        @Field("grant_type") grant_type: String?,
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Response<TokenResponse>


    @FormUrlEncoded
    @POST("/api/login")
    suspend fun getToken1(
        @Field("email") username: String?,
        @Field("password") password: String?
    ): Response<LoginTokenResponse>

    @POST("/api/SalesAppLogin/ViaOtp")
    suspend fun getOtp(@Body otpRequest: OtpRequest): Response<JsonElement>


   @GET("/api/appVersion/SupplierApp")
   suspend fun getVersion():Response<JsonElement>

    @GET("/api/Suppliers/GenotpForSupplierLogin")
    suspend fun getOtp1(@Query("Mobile") Mobile:String):Response<JsonElement>



    @GET("/api/v1/employees")
    suspend fun employees():Response<employeesResponsModel>



}