package com.example.retrofitproject.mainActivity

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitproject.viewModelFactory.AuthViewModelFactory
import com.example.retrofitproject.LoginTokenResponse
import com.example.retrofitproject.R
import com.example.retrofitproject.observe
import com.example.retrofitproject.repository.AppRepository
import com.example.retrofitproject.response.Response
import com.example.retrofitproject.sharePrefrence.SharePrefs
import com.example.retrofitproject.viewModel.LoginViewModel
import com.google.gson.Gson
import com.google.gson.JsonElement


class MainActivity : AppCompatActivity() {
    lateinit var text1:TextView
    lateinit var btn:Button
    lateinit var loginViewModel: LoginViewModel
    lateinit var sharePrefs: SharePrefs
    lateinit var contaxt:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appRepository = AppRepository(applicationContext)


        loginViewModel = ViewModelProvider(
            this,
            AuthViewModelFactory(application, appRepository)
        ).get(LoginViewModel::class.java)

    }

}














//        loginViewModel.callToken("eve.holt@reqres.in","cityslicka")
//     loginViewModel.getOtp(OtpRequest("9522392801", "", BuildConfig.VERSION_NAME, Build.VERSION.RELEASE, Build.MODEL, Utils.getDeviceUniqueID(this)!!))
//        loginViewModel.getVesionData()
//loginViewModel.getOtp1("9109900586")

//observe(loginViewModel.tokenData, ::handleResult)
//        observe(loginViewModel.getOtp,::handleOtp)
//        observe(loginViewModel.versionData,::handleVersion)
//        observe(loginViewModel.getOtp1,::handlegetOtp1)
//
//fun handleResult(it: Response<LoginTokenResponse>) {
//        when (it) {
//            is Response.Loading -> {
//            }
//            is Response.Success -> {
//                it.data?.let {
//                    // hideProgressDialog()
//
//                    text1.setText(it.token)
//
//
//                    SharePrefs.getInstance(applicationContext)!!.putString(SharePrefs.TOKEN,it.token)
//
//                    println("LoginResponse::" + Gson().toJson(it))
//
//
//                  var value= SharePrefs.getInstance(applicationContext)!!.getString(SharePrefs.TOKEN)
//                   SharePrefs.getInstance(applicationContext)!!.upDate(SharePrefs.TOKEN,"atul")
//                    var getupdateValue= SharePrefs.getInstance(applicationContext)!!.getString(
//                        SharePrefs.TOKEN)
//                    println("value="+value)
//                    println("update="+getupdateValue)
//
//                   SharePrefs.getInstance(applicationContext)!!.delete(SharePrefs.TOKEN)
//                    var delete= SharePrefs.getInstance(applicationContext)!!.getString(SharePrefs.TOKEN)
//                    println("Delete="+delete)
//
//                }
//            }
//            is Response.Error -> {
//                Log.e(TAG, "onCreate:565555555 " )
//            }
//        }
//    }
//
//
//    fun handleOtp(it: Response<JsonElement>){
//        when (it) {
//            is Response.Loading -> {
//            }
//            is Response.Success -> {
//                it.data?.let {
//                    // hideProgressDialog()
//                    println("LoginResponse::" + Gson().toJson(it))
//
//                }
//            }
//            is Response.Error -> {
//                Log.e(TAG, "onCreate:565555555 " )
//            }
//        }
//    }
//
//    fun handleVersion(it: Response<JsonElement>){
//        when (it) {
//            is Response.Loading -> {
//            }
//            is Response.Success -> {
//                it.data?.let {
//                    // hideProgressDialog()
//                    println("LoginResponse1::" + Gson().toJson(it))
//
//                }
//            }
//            is Response.Error -> {
//                Log.e(TAG, "onCreate:565555555 " )
//            }
//        }
//    }
//
//    fun handlegetOtp1(it: Response<JsonElement>){
//        when (it) {
//            is Response.Loading -> {
//            }
//            is Response.Success -> {
//                it.data?.let {
//                    // hideProgressDialog()
//                    println("LoginResponse1::" + Gson().toJson(it))
//
//                }
//            }
//            is Response.Error -> {
//                Log.e(TAG, "onCreate:565555555 " )
//            }
//        }
//    }
