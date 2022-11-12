package com.example.myweatherapp.data.api

import com.example.myweatherapp.cons.YANDEX_API_KEY
import com.example.myweatherapp.cons.YANDEX_ENDPOINT
import com.example.myweatherapp.cons.YANDEX_LAT
import com.example.myweatherapp.cons.YANDEX_LON
import com.example.myweatherapp.domain.dto.WeatherDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherApi {
    @GET(YANDEX_ENDPOINT)
    suspend fun getWeather(
        @Header(YANDEX_API_KEY) key: String,
        @Query(YANDEX_LAT) lat: Double,
        @Query(YANDEX_LON) lon: Double
    ): Response<WeatherDTO>
}