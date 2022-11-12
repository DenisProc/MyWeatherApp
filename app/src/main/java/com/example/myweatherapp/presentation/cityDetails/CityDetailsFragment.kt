package com.example.myweatherapp.presentation.cityDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myweatherapp.databinding.FragmentCityDetailsBinding
import com.example.myweatherapp.domain.Weather

class CityDetailsFragment : Fragment() {
    private lateinit var binding: FragmentCityDetailsBinding
    private val viewModel: CityDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityDetailsBinding.inflate(inflater)
        viewModel.getCityWeather()
        initDetails(viewModel.getCityWeather())

        return binding.root
    }

    private fun initDetails(weather: Weather?) = with(binding) {
        val coords = "${weather?.city?.lat}/${weather?.city?.lon}"
        detailsTemperature.text = weather?.temperature
        detailsFeelsLike.text = weather?.felt
        detailsCityName.text = weather?.city?.cityName
        detailsCoordinates.text = coords
    }

    companion object {
        @JvmStatic
        fun newInstance() = CityDetailsFragment()
    }
}
