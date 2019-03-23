package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.Location.GeoCountryCity
import com.task2trip.android.Model.MockData
import com.task2trip.android.View.SearchLocationView
import retrofit2.Call

class SearchLocationPresenter(val view: SearchLocationView, context: Context) : BasePresenter(context) {

    fun getCountryAndCityByText(wordForSearch: String) {
        val req: Call<List<GeoCountryCity>> = getApi().getCountryAndCity(wordForSearch)
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    view.onSearchLocationResult(response.body() ?: MockData.getEmptyGeoLocationsList())
                }
            }
        }
    }
}