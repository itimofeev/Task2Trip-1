package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.TextView
import com.task2trip.android.Model.GeoCountryCity
import com.task2trip.android.R

class CountryAndCityHolder(itemView: View) {
    private var tvCountry: TextView = itemView.findViewById(R.id.tvCountry)
    private var tvCity: TextView = itemView.findViewById(R.id.tvCity)

    fun setData(item: GeoCountryCity) {
        tvCountry.text = item.description
        tvCity.text = item.name
    }
}