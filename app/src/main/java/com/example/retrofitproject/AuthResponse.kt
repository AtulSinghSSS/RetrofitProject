package com.example.retrofitproject

import com.google.gson.annotations.SerializedName
import java.io.Serializable

 class AuthResponse: Serializable {
     @SerializedName("Status")
     var status: Boolean = false
     @SerializedName("message")
     val message: String=""
     @SerializedName("people")
     val peopleModel: PeopleModel? = null
 }