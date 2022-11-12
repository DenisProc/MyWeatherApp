package com.example.myweatherapp.presentation.weatherList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.WeatherListCardviewBinding
import com.example.myweatherapp.domain.Weather
import com.example.myweatherapp.extensions.showSnack

class WeatherListAdapter(val listener: AdapterItemClickListener) :
    RecyclerView.Adapter<WeatherListAdapter.WeatherListHolder>() {

    private var cityList: List<Weather> = emptyList()

    class WeatherListHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = WeatherListCardviewBinding.bind(item)

        fun bind(weather: Weather, listener: AdapterItemClickListener) = with(binding) {
            cardviewCityName.text = weather.city.cityName
            cardviewTemperature.text = "${weather.temperature} C°"
            weatherListCardview.setOnClickListener {
                listener.onAdapterItemClick(weather)
                listener.showSnack(it, "Показана детализация города ${weather.city.cityName}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_list_cardview, parent, false)
        return WeatherListHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherListHolder, position: Int) {
        holder.bind(cityList[position], listener)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    fun refresh() {
        notifyDataSetChanged()
    }

    fun submitList(cityListSub: List<Weather>) {
        cityList = cityListSub
        refresh()
    }

    interface AdapterItemClickListener {
        fun onAdapterItemClick(weather: Weather)
    }
}