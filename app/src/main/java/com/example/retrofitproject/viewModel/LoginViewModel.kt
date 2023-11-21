package com.example.retrofitproject.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofitproject.PeopleModel
import com.example.retrofitproject.R
import com.example.retrofitproject.TokenResponse
import com.example.retrofitproject.login.LoginRequest
import com.example.retrofitproject.networkUtils.NetworkUtils
import com.example.retrofitproject.repository.AppRepository
import com.example.retrofitproject.response.Response
import com.example.retrofitproject.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel(
    app: Application, private val repository: AppRepository
) : AndroidViewModel(app) {


    private val peopleLoginLiveData = MutableLiveData<Response<PeopleModel>>()
    val peopleData: LiveData<Response<PeopleModel>>
        get() = peopleLoginLiveData


    private val tokenLiveData = MutableLiveData<Response<TokenResponse>>()
    val tokenData: LiveData<Response<TokenResponse>>
        get() = tokenLiveData


    fun doLogin(loginRequest: LoginRequest) = viewModelScope.launch(Dispatchers.IO) {
        if (Utils.isNullOrEmpty(loginRequest.mobile)) {

            peopleLoginLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.valid_empty_mobile_number)))
        } else if (loginRequest.mobile.length < 10) {
            peopleLoginLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.valid_mobile_number)))
        } else if (Utils.isNullOrEmpty(loginRequest.password)) {
            peopleLoginLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.valid_empty_password)))
        } else if (loginRequest.password.length < 6) {
            peopleLoginLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.valid_password)))
        } else {
            login(loginRequest)

        }
    }


    private suspend fun login(loginRequest: LoginRequest) {
        if (NetworkUtils.isInternetAvailable(getApplication<Application>())) {
            peopleLoginLiveData.postValue(Response.Loading())
            val result = repository.loginUser(loginRequest)
            if (result.body()!!.status) {
                peopleLoginLiveData.postValue(Response.Success(result.body()!!.peopleModel))
            } else {
                peopleLoginLiveData.postValue(Response.Error(result.body()!!.message))
            }
        } else {
            peopleLoginLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.internet_connection)))
        }
    }

    fun callToken(grantType: String, username: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            if (NetworkUtils.isInternetAvailable(getApplication<Application>())) {
                tokenLiveData.postValue(Response.Loading())
                val result = repository.getToken(grantType, username, password)
                if (result.body() != null) {
                    // getApplication<MyApplication>().token = result.body()!!.access_token.toString()
                    tokenLiveData.postValue(Response.Success(result.body()))
                } else {
                    tokenLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.msg_improper_response_server)))
                }
            } else {
                tokenLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.internet_connection)))
            }
        }

}











//
//fun callToken(email: String, password: String?) =
//    viewModelScope.launch(Dispatchers.IO) {
//        if (NetworkUtils.isInternetAvailable(getApplication<Application>())) {
//
//            tokenLiveData.postValue(Response.Loading())
//            val result = repository.getToken(email, password)
//            if (result.body() != null) {
//
//                tokenLiveData.postValue(Response.Success(result.body()))
//                Log.e(TAG, "callToken: " + result.body())
//            } else {
//                tokenLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.msg_improper_response_server)))
//            }
//        } else {
//            tokenLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.internet_connection)))
//        }
//    }
//
//
////    fun getOtp(otpRequest: OtpRequest) = viewModelScope.launch(Dispatchers.IO) {
////
////        if (NetworkUtils.isInternetAvailable(getApplication<Application>())) {
////
////            getOtpLiveData.postValue(Response.Loading())
////            val result = repository.getOtp(otpRequest)
////            if (result.body() != null) {
////
////                getOtpLiveData.postValue(Response.Success(result.body()))
////                Log.e(TAG, "callToken: " + result.body())
////            } else {
////                getOtpLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.msg_improper_response_server)))
////            }
////        } else {
////            getOtpLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.internet_connection)))
////        }
////
////    }
//
//
//fun getOtp1(mobile: String) = viewModelScope.launch(Dispatchers.IO) {
//
//    if (NetworkUtils.isInternetAvailable(getApplication<Application>())) {
//
//        getOtpLiveData.postValue(Response.Loading())
//        val result = repository.getOtp1(mobile)
//        if (result.body() != null) {
//
//            getOtpLiveData.postValue(Response.Success(result.body()))
//            Log.e(TAG, "callToken: " + result.body())
//        } else {
//            getOtpLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.msg_improper_response_server)))
//        }
//    } else {
//        getOtpLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.internet_connection)))
//    }
//
//}
//
//
//fun getVesionData() = viewModelScope.launch(Dispatchers.IO) {
//
//    if (NetworkUtils.isInternetAvailable(getApplication<Application>())) {
//
//        getOtpLiveData.postValue(Response.Loading())
//        val result = repository.getVersion()
//        if (result.body() != null) {
//
//            getOtpLiveData.postValue(Response.Success(result.body()))
//            Log.e(TAG, "callToken1: " + result.body())
//        } else {
//            getOtpLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.msg_improper_response_server)))
//        }
//    } else {
//        getOtpLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.internet_connection)))
//    }
//
//}

