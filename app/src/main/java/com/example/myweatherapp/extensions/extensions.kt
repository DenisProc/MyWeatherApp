package com.example.myweatherapp.extensions

import android.view.View
import androidx.fragment.app.Fragment
import com.example.myweatherapp.presentation.weatherList.WeatherListAdapter
import com.google.android.material.snackbar.Snackbar

fun WeatherListAdapter.AdapterItemClickListener.showSnack(view: View, string: String) {
    Snackbar.make(view, string, Snackbar.LENGTH_LONG).show()
}

fun Fragment.openFragment(fragment: Fragment, container: Int) {
    requireActivity()
        .supportFragmentManager
        .beginTransaction()
        .addToBackStack("")
        .add(container, fragment)
        .commit()
}