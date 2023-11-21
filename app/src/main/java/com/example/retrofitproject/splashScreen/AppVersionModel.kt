package com.example.retrofitproject.splashScreen

import com.google.gson.annotations.SerializedName

class AppVersionModel {
    @SerializedName("id")
    var id = 0

    @SerializedName("App_version")
    var app_version: String? = null

    @SerializedName("isCompulsory")
    var isCompulsory = false

    @SerializedName("createdDate")
    var createdDate: String? = null
}