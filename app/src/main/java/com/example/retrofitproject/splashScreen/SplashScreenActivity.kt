package com.example.retrofitproject.splashScreen


import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitproject.BuildConfig
import com.example.retrofitproject.R

import com.example.retrofitproject.databinding.ActivitySplashScreenBinding
import com.example.retrofitproject.home.HomeFragment
import com.example.retrofitproject.mainActivity.MainActivity
import com.example.retrofitproject.networkUtils.NetworkUtils
import com.example.retrofitproject.observe
import com.example.retrofitproject.repository.AppRepository
import com.example.retrofitproject.response.Response
import com.example.retrofitproject.sharePrefrence.SharePrefs
import com.example.retrofitproject.utils.ViewUtils.Companion.snackbar


class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    lateinit var splashViewModel: SplashViewModel
    private var appVersion: String = ""
    private var isCompulsory: Boolean = false
    private var isPresent: Boolean = false
    private var isLogin: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chackInternetConection()

        splashViewModel = ViewModelProvider(
            this,
            SplashViewModelFactory(application, AppRepository(applicationContext))
        ).get(SplashViewModel::class.java)
      //  observe(splashViewModel.versionData, ::handleResult)
        observe(splashViewModel.employeesLiveData, ::employeesResult)

    }

    fun chackInternetConection() {
        if (NetworkUtils.isInternetAvailable(this)) {

            splashScreen()

        } else {
            Toast.makeText(
                applicationContext,
                "Internet not connect",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    fun splashScreen() {
        Handler().postDelayed({

         //   splashViewModel.callAppVersion()
            splashViewModel.employees()
//            startActivity(Intent(applicationContext, MainActivity::class.java))
//            finish()
        }, 2000)
    }


    private fun handleResult(it: Response<ArrayList<AppVersionModel>>) {
        when (it) {
            is Response.Loading -> {

            }
            is Response.Success -> {
                it.data?.let {
                    checkVersionUpdate(it)
                }
            }
            is Response.Error -> {

                binding.root.snackbar(it.errorMesssage.toString())
            }
        }
    }

    private fun employeesResult(it: Response<employeesResponsModel>) {
        when (it) {
            is Response.Loading -> {

            }
            is Response.Success -> {
                it.data?.let {
                    Log.e(TAG, "handleResult: ${it.data}", )
                }
            }
            is Response.Error -> {
                binding.root.snackbar(it.errorMesssage.toString())
            }
        }
    }


    fun checkVersionUpdate(versionList: ArrayList<AppVersionModel>) {
        try {
            if (versionList != null && versionList.size != 0) {
                for (i in versionList.indices) {
                    appVersion = versionList[i].app_version.toString()
                    isCompulsory = versionList[i].isCompulsory
                    if (BuildConfig.VERSION_NAME.equals(appVersion) && isCompulsory) {
                        isPresent = true
                    } else {
                        isPresent = false
                    }
                    if (isPresent) {
                        isLogin= SharePrefs.getInstance(applicationContext).getBoolean(SharePrefs.IS_LOGIN)!!
                        if (isLogin){

                            startActivity(Intent(applicationContext, MainActivity::class.java))
                            finish()

                        }else{
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                            finish()
                        }

                    } else {
                        Log.e(TAG, "checkVersionUpdate: 1.84")
                    }
                }
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }


    }


}