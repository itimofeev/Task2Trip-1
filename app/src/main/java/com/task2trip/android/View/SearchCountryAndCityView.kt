package com.task2trip.android.View

import com.task2trip.android.Model.GeoCountryCity

interface SearchCountryAndCityView {
    fun onSearchCountryAndCityResult(items: List<GeoCountryCity>)
}