package com.example.andynovelapi.internetApi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class UserApiManager (baseUrl: String, appName: String){

    private var userApiService: UserApiService? = null

    init {
        val client = OkHttpClient.Builder()
        client.addInterceptor {
            val original = it.request()
            val request = original.newBuilder()
                .header("User-Agent", appName)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .method(original.method(), original.body())
                .build()
            return@addInterceptor it.proceed(request)
        }

        val httpClient = client.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()

         userApiService = retrofit.create(UserApiService::class.java)
    }

    fun getUserApiService() : UserApiService?{
        return userApiService
    }
}