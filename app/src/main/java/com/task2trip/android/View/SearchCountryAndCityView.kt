package com.task2trip.android.View

import com.task2trip.android.Model.Location.GeoCountryCity

interface SearchCountryAndCityView {
    fun onSearchCountryAndCityResult(items: List<GeoCountryCity>)
}