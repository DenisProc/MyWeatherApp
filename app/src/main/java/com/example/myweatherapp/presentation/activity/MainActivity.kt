package com.example.myweatherapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.ActivityMainBinding
import com.example.myweatherapp.presentation.weatherList.WeatherListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startFragmentWeatherList()
    }

    private fun startFragmentWeatherList(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, WeatherListFragment.newInstance())
            .commit()
    }
}