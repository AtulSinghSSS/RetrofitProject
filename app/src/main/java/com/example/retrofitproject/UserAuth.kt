package com.example.retrofitproject

import com.google.gson.annotations.SerializedName

class UserAuth {
    @SerializedName("Password")
    var password: String? = null

    @SerializedName("UserName")
    var userName: String? = null
}