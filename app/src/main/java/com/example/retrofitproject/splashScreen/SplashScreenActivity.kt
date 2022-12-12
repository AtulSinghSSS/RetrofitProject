package com.example.retrofitproject.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import com.example.retrofitproject.R
import com.example.retrofitproject.databinding.ActivitySplashScreenBinding
import com.example.retrofitproject.mainActivity.MainActivity
import com.example.retrofitproject.networkUtils.NetworkUtils

class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chackInternetConection()
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

            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }, 2000)
    }
}