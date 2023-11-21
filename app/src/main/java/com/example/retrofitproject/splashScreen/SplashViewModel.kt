package com.example.retrofitproject.splashScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitproject.R
import com.example.retrofitproject.SingleLiveEvent
import com.example.retrofitproject.TokenResponse
import com.example.retrofitproject.networkUtils.NetworkUtils
import com.example.retrofitproject.repository.AppRepository
import com.example.retrofitproject.response.Response
import com.google.gson.JsonElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(app: Application, private val repository: AppRepository):AndroidViewModel(app){

    private val versionLiveData = SingleLiveEvent<Response<ArrayList<AppVersionModel>>>()
    val versionData: SingleLiveEvent<Response<ArrayList<AppVersionModel>>>
        get() = versionLiveData

    private val tokenLiveData = SingleLiveEvent<Response<TokenResponse>>()
    val tokenData: SingleLiveEvent<Response<TokenResponse>>
        get() = tokenLiveData


    fun callAppVersion() = viewModelScope.launch(Dispatchers.IO) {
        if (NetworkUtils.isInternetAvailable(getApplication<Application>())) {
            versionLiveData.postValue(Response.Loading())
            val result = repository.getAppversion()
            if (result.body() != null && result.body()!!.size > 0) {
                versionLiveData.postValue(Response.Success(result.body()))
            } else {
                versionLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.msg_improper_response_server)))
            }
        } else {
            versionLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.internet_connection)))
        }
    }


    fun callToken(grantType: String, username: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            if (NetworkUtils.isInternetAvailable(getApplication<Application>())) {
                tokenLiveData.postValue(Response.Loading())
                val result = repository.getToken(grantType, username, password)
                if (result.body() != null) {
                    tokenLiveData.postValue(Response.Success(result.body()))
                } else {
                    tokenLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.msg_improper_response_server)))
                }
            } else {
                tokenLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.internet_connection)))
            }
        }


}