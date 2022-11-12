package com.example.myweatherapp.domain

import com.example.myweatherapp.domain.dto.WeatherDTO
import retrofit2.Response

interface WeatherRepository {
    var worldCityList: List<Weather>
    var russianCityList: List<Weather>
    suspend fun getWeather(city: City): Response<WeatherDTO>?
    fun getCityForDetails(): Weather?
    fun saveCityForDetails(save: Weather)
    fun getRussianWeatherList():List<Weather>
    fun getWorldWeatherList(): List<Weather>
    fun saveCityWeather(weatherForSave: Weather)

}