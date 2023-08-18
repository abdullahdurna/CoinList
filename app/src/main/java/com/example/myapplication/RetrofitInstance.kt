package com.example.myapplication

import api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL_BINANCE = "https://api.binance.com/"
    private const val BASE_URL_FLASK = "http://10.0.2.2:5000/"

    val apiServiceBinance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_BINANCE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val apiServiceFlask: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_FLASK)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
