package com.example.myweatherapp.presentation.weatherList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.FragmentWeatherListBinding
import com.example.myweatherapp.domain.TypeOfCityList
import com.example.myweatherapp.domain.Weather
import com.example.myweatherapp.extensions.openFragment
import com.example.myweatherapp.presentation.cityDetails.CityDetailsFragment
import kotlinx.coroutines.*

class WeatherListFragment : Fragment(), WeatherListAdapter.AdapterItemClickListener,
    CoroutineScope by CoroutineScope(
        SupervisorJob()
    ) {
    private lateinit var binding: FragmentWeatherListBinding
    private val adapter = WeatherListAdapter(this)
    private val viewModel: WeatherListViewModel by viewModels()
    private var isRussian = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            launch {
                viewModel.getCityWeatherList(TypeOfCityList.WORLD)
            }
            launch {
                viewModel.weatherList.observe(viewLifecycleOwner) { list ->
                    adapter.submitList(list)
                }
            }
            launch {
                initRecyclerView()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherListBinding.inflate(inflater)
        initBtns()
        return binding.root
    }

    private fun initRecyclerView() {
        binding.apply {
            weatherListRecycler.layoutManager = LinearLayoutManager(activity)
            weatherListRecycler.adapter = adapter
        }
        adapter.refresh()
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherListFragment()
    }

    private fun initBtns() {
        if (isRussian) {
            isRussian = false
            initChangeCityBtn(R.drawable.world_btn, TypeOfCityList.WORLD)
        } else {
            isRussian = true
            initChangeCityBtn(R.drawable.russian_btn, TypeOfCityList.RUSSIAN)
        }
    }

    override fun onAdapterItemClick(weather: Weather) {
        viewModel.saveCityWeather(weather)
        this.openFragment(CityDetailsFragment.newInstance(), R.id.fragment_container)
    }

    override fun onDestroyView() {
        coroutineContext.cancelChildren()
        super.onDestroyView()
    }

    fun initChangeCityBtn(imageIndex: Int, typeOfCityList: TypeOfCityList) = with(binding) {
        changeCitysBtn.setOnClickListener {
            changeCitysBtn.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    imageIndex
                )
            )
            CoroutineScope(SupervisorJob()).launch { viewModel.getCityWeatherList(typeOfCityList) }
            viewModel.weatherList.observe(viewLifecycleOwner) { list ->
                adapter.submitList(list)
            }
        }
    }

}