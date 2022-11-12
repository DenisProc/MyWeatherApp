package com.example.myweatherapp.presentation.cityDetails

import androidx.lifecycle.ViewModel
import com.example.myweatherapp.data.repository.RepositoryImpl
import com.example.myweatherapp.domain.Weather
import com.example.myweatherapp.domain.WeatherRepository

class CityDetailsViewModel() : ViewModel() {
    private val repository: WeatherRepository = RepositoryImpl()

    fun getCityWeather(): Weather? {
        return repository.getCityForDetails()
    }

}