package com.task2trip.android.UI

import android.widget.Filter
import com.task2trip.android.Model.GeoCountryCity
import java.util.*

class CountryAndCityFilter: Filter() {
    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val filterResults = Filter.FilterResults()
        filterResults.values = null
        filterResults.count - 0
        return filterResults
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        //
    }

    private fun searchEngine(listForSearch: List<GeoCountryCity>?, strForSearch: String): List<GeoCountryCity> {
        val filterList = ArrayList<GeoCountryCity>()
        if (listForSearch != null) {
            for (i in listForSearch.indices) {
                val item = listForSearch[i]
                // Ищем в любой части названия
                val nameStr = item.description.toLowerCase(Locale.getDefault())
                val searchStr = strForSearch.toLowerCase(Locale.getDefault())
                if (nameStr.contains(searchStr)) {
                    filterList.add(item)
                }
            }
        }
        return filterList
    }
}