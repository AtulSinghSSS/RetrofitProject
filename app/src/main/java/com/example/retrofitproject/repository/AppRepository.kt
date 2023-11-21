package com.example.retrofitproject.repository

import android.content.Context
import com.example.retrofitproject.login.LoginRequest
import com.example.retrofitproject.myApplication.MyApplication

class AppRepository( applicationContext: Context) {

    var apiService = (applicationContext as MyApplication).apiService

    suspend fun getAppversion() = apiService.getAppversion()

    suspend fun loginUser(loginRequest: LoginRequest) = apiService.doLogin(
        loginRequest.mobile,
        loginRequest.password,
        loginRequest.fcmId,
        loginRequest.currentAPKversion,
        loginRequest.phoneOSversion,
        loginRequest.userDeviceName,
        loginRequest.deviceId,
        loginRequest.deviceId
    )

    suspend fun getToken(grant_type: String,  username: String,password: String) = apiService.getToken( grant_type,username,password)

    suspend fun getToken1(email: String, password: String?) = apiService.getToken1( email, password)

//    suspend fun getOtp(otpRequest: OtpRequest)=apiService.getOtp(otpRequest)

    suspend fun getVersion()=apiService.getVersion()

    suspend fun getOtp1(mobile:String)=apiService.getOtp1(mobile)
    suspend fun employees()=apiService.employees()

}

