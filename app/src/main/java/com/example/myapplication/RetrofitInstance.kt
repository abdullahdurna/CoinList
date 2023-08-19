package com.example.myapplication

import api.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL_BINANCE = "https://api.binance.com/"
    private const val BASE_URL_FLASK = "http://10.0.2.2:5000/"

    private val client = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.MINUTES)  // Bağlantı zaman aşımını 5 dakika olarak ayarlar.
        .readTimeout(5, TimeUnit.MINUTES)     // Okuma zaman aşımını 5 dakika olarak ayarlar.
        .writeTimeout(5, TimeUnit.MINUTES)    // Yazma zaman aşımını 5 dakika olarak ayarlar.
        .build()

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
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
