package com.example.myweatherapp.data.api

import com.example.myweatherapp.cons.YANDEX_DOMAIN
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(YANDEX_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }
    val api: WeatherApi by lazy { retrofit.create(WeatherApi::class.java) }
}