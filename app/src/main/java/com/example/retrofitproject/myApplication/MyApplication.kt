package com.example.retrofitproject.myApplication

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.retrofitproject.apiService.APIService
import com.example.retrofitproject.apiService.RetrofitHelper

class MyApplication:Application(),LifecycleObserver {
    var mInstance: MyApplication? = null
    lateinit var apiService: APIService

    @Synchronized
    fun getInstance(): MyApplication? {
        return mInstance
    }

    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this)
        apiService = RetrofitHelper.getInstance(applicationContext)
        mInstance=this



    }
}