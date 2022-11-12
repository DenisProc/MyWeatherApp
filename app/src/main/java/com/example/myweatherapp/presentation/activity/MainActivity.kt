package com.example.myweatherapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.ActivityMainBinding
import com.example.myweatherapp.presentation.weatherList.WeatherListFragment
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        randomLoadingState()

        startFragmentWeatherList()
    }

    private fun startFragmentWeatherList() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, WeatherListFragment.newInstance())
            .commit()
    }

    private fun randomLoadingState() {
        when (val random = Random.nextInt(1, 3)) {
            1 -> Snackbar.make(
                binding.fragmentContainer,
                "Ошибка. Рандом сказал что загрузка не получилась $random",
                Snackbar.LENGTH_LONG
            ).show()
            2 -> Snackbar.make(
                binding.fragmentContainer,
                "Выполняется загрузка. Так сказал рандом $random",
                Snackbar.LENGTH_LONG
            ).show()
            3 -> Snackbar.make(
                binding.fragmentContainer,
                "Загрузка прошла успешно. Так сказал рандом $random",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}