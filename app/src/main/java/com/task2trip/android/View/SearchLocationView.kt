package com.task2trip.android.View

import com.task2trip.android.Model.Location.GeoCountryCity

interface SearchLocationView {
    fun onSearchLocationResult(items: List<GeoCountryCity>)
}