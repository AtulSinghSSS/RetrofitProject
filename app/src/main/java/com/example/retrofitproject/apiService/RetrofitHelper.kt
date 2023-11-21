package com.example.retrofitproject.apiService

import android.content.Context

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitHelper {

    var BASE_URL = "https://reqres.in/"
    var BASE_URL1 = "https://uat.shopkirana.in/"
    var BASE_URL2 = "https://dummy.restapiexample.com/"
    fun getInstance(context: Context): APIService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .readTimeout(5, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(Interceptor addInterceptor@{ chain: Interceptor.Chain ->
                var response: Response? = null
                try {
                    val request = chain.request()
                    response = chain.proceed(request)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return@addInterceptor response ?: Response.Builder()
                    .code(200).request(chain.request())
                    .build()
            })
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request().newBuilder()
                    .build()
                return@Interceptor chain.proceed(request)
            })
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    private fun printMsg(msg: String) {
        val chunkCount = msg.length / 4050 // integer division
        for (i in 0..chunkCount) {
            val max = 4050 * (i + 1)
            if (max >= msg.length) {
                println(msg.substring(4050 * i))
            } else {
                println(msg.substring(4050 * i, max))
            }
        }
    }
}