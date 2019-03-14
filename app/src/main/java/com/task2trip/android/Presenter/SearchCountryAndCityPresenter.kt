package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.GeoCountryCity
import com.task2trip.android.Model.MockData
import com.task2trip.android.View.SearchCountryAndCityView
import retrofit2.Call

class SearchCountryAndCityPresenter(val view: SearchCountryAndCityView, context: Context) : BasePresenter(context) {

    fun getCountryAndCityByText(wordForSearch: String) {
        val req: Call<List<GeoCountryCity>> = getApi().getCountryAndCity(wordForSearch)
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    view.onSearchCountryAndCityResult(response.body() ?: MockData.getEmptyGeoLocationsList())
                }
            }
        }
    }
}