package com.example.retrofitproject.repository

import android.content.Context
import com.example.retrofitproject.myApplication.MyApplication

class AppRepository( applicationContext: Context) {

    var apiService = (applicationContext as MyApplication).apiService

    suspend fun getAppversion()=apiService.getAppversion()

    suspend fun getToken(email: String, password: String?) = apiService.getToken( email, password)

//    suspend fun getOtp(otpRequest: OtpRequest)=apiService.getOtp(otpRequest)

    suspend fun getVersion()=apiService.getVersion()

    suspend fun getOtp1(mobile:String)=apiService.getOtp1(mobile)

}

