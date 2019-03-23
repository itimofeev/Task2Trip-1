package com.task2trip.android.UI.Widget

import com.task2trip.android.Model.Location.GeoCountryCity

interface SearchLocationFieldCallback {
    fun onTextLocationChanged(text: String)
    fun onItemLocationChanged(item: GeoCountryCity)
    fun onLocationClick()
}