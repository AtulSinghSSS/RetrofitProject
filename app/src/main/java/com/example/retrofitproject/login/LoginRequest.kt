package com.example.retrofitproject.login
data class LoginRequest(val mobile: String,
                        val password: String,
                        val fcmId:String,
                        val currentAPKversion:String,
                        val phoneOSversion:String,
                        val userDeviceName:String,
                        val deviceId:String
                        )