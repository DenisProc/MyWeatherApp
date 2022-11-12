package com.example.myweatherapp.presentation.weatherList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.data.repository.RepositoryImpl
import com.example.myweatherapp.domain.City
import com.example.myweatherapp.domain.TypeOfCityList
import com.example.myweatherapp.domain.Weather
import com.example.myweatherapp.domain.WeatherRepository

class WeatherListViewModel : ViewModel() {
    private val repository: WeatherRepository = RepositoryImpl()
    val weatherList: MutableLiveData<List<Weather>> by lazy { MutableLiveData<List<Weather>>() }

    suspend fun getCityWeatherList(typeOfCityList: TypeOfCityList) {
        val list = mutableListOf<Weather>()
        val incomingList = when (typeOfCityList) {
            TypeOfCityList.WORLD -> repository.getWorldWeatherList()
            TypeOfCityList.RUSSIAN -> repository.getRussianWeatherList()
        }
        for (weathers in incomingList) {
            val dto = repository.getWeather(weathers.city)
            val weather = Weather(
                City(weathers.city.cityName, weathers.city.lat, weathers.city.lon),
                dto?.body()?.fact?.temp.toString(),
                dto?.body()?.fact?.feels_like.toString()
            )
            list.add(weather)
            weatherList.postValue(list)
        }
    }

    fun saveCityWeather(weather: Weather) {
        repository.saveCityWeather(weather)
    }
}