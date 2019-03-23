package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.TextView
import com.task2trip.android.Model.Location.GeoCountryCity
import com.task2trip.android.R

class CountryAndCityHolder(itemView: View) {
    private var tvCountry: TextView = itemView.findViewById(R.id.tvCountry)
    private var tvCity: TextView = itemView.findViewById(R.id.tvCity)

    fun setData(item: GeoCountryCity) {
        if (item.description.isNullOrEmpty()) {
            tvCountry.visibility = View.GONE
        } else {
            tvCountry.visibility = View.VISIBLE
            tvCountry.text = item.description
        }
        if (item.name.isNullOrEmpty()) {
            tvCity.visibility = View.GONE
        } else {
            tvCity.visibility = View.VISIBLE
            tvCity.text = item.name
        }
    }
}