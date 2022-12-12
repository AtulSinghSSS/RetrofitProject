package com.example.retrofitproject

import com.google.gson.annotations.SerializedName

class LoginTokenResponse {
    @SerializedName("token")
    var token: String = ""
}