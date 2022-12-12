package com.example.retrofitproject.viewModel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofitproject.LoginTokenResponse
import com.example.retrofitproject.networkUtils.NetworkUtils
import com.example.retrofitproject.R
import com.example.retrofitproject.response.Response
import com.example.retrofitproject.repository.AppRepository
import com.google.gson.JsonElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel(
    app: Application, private val repository: AppRepository
) : AndroidViewModel(app) {

    private val tokenLiveData = MutableLiveData<Response<LoginTokenResponse>>()
    val tokenData: LiveData<Response<LoginTokenResponse>>
        get() = tokenLiveData

    private val getOtpLiveData = MutableLiveData<Response<JsonElement>>()
    val getOtp: LiveData<Response<JsonElement>>
        get() = getOtpLiveData

    private val getOtp1LiveData = MutableLiveData<Response<JsonElement>>()
    val getOtp1: LiveData<Response<JsonElement>>
        get() = getOtp1LiveData


    private val versionLiveData= MutableLiveData<Response<JsonElement>>()
    val versionData: LiveData<Response<JsonElement>>
        get() = versionLiveData



    fun callToken(email: String, password: String?) =
        viewModelScope.launch(Dispatchers.IO) {
            if (NetworkUtils.isInternetAvailable(getApplication<Application>())) {

                tokenLiveData.postValue(Response.Loading())
                val result = repository.getToken(email, password)
                if (result.body() != null) {

                    tokenLiveData.postValue(Response.Success(result.body()))
                    Log.e(TAG, "callToken: " + result.body())
                } else {
                    tokenLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.msg_improper_response_server)))
                }
            } else {
                tokenLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.internet_connection)))
            }
        }


//    fun getOtp(otpRequest: OtpRequest) = viewModelScope.launch(Dispatchers.IO) {
//
//        if (NetworkUtils.isInternetAvailable(getApplication<Application>())) {
//
//            getOtpLiveData.postValue(Response.Loading())
//            val result = repository.getOtp(otpRequest)
//            if (result.body() != null) {
//
//                getOtpLiveData.postValue(Response.Success(result.body()))
//                Log.e(TAG, "callToken: " + result.body())
//            } else {
//                getOtpLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.msg_improper_response_server)))
//            }
//        } else {
//            getOtpLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.internet_connection)))
//        }
//
//    }


    fun getOtp1(mobile: String) = viewModelScope.launch(Dispatchers.IO) {

        if (NetworkUtils.isInternetAvailable(getApplication<Application>())) {

            getOtpLiveData.postValue(Response.Loading())
            val result = repository.getOtp1(mobile)
            if (result.body() != null) {

                getOtpLiveData.postValue(Response.Success(result.body()))
                Log.e(TAG, "callToken: " + result.body())
            } else {
                getOtpLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.msg_improper_response_server)))
            }
        } else {
            getOtpLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.internet_connection)))
        }

    }


    fun getVesionData() = viewModelScope.launch(Dispatchers.IO) {

        if (NetworkUtils.isInternetAvailable(getApplication<Application>())) {

            getOtpLiveData.postValue(Response.Loading())
            val result = repository.getVersion()
            if (result.body() != null) {

                getOtpLiveData.postValue(Response.Success(result.body()))
                Log.e(TAG, "callToken1: " + result.body())
            } else {
                getOtpLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.msg_improper_response_server)))
            }
        } else {
            getOtpLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.internet_connection)))
        }

    }

}

