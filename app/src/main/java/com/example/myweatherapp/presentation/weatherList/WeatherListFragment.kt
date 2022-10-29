package com.example.myweatherapp.presentation.weatherList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myweatherapp.databinding.FragmentWeatherListBinding

class WeatherListFragment : Fragment() {
    private lateinit var binding: FragmentWeatherListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherListBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherListFragment()
    }
}