package com.task2trip.android.UI

import android.widget.Filter
import com.task2trip.android.Model.Location.GeoCountryCity
import kotlin.collections.ArrayList

class CountryAndCityFilter: Filter() {
    private val items: ArrayList<GeoCountryCity> = ArrayList()
    private var lastWord = ""

    fun setItems(items: ArrayList<GeoCountryCity>) {
        this.items.clear()
        this.items.addAll(items)
        val result: FilterResults = performFiltering(lastWord)
        publishResults(lastWord, result)
    }

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val text: String = constraint?.toString() ?: ""
        lastWord = text
        val filterResults = FilterResults()
        if (lastWord.isEmpty()) {
            return filterResults
        }
        val resultItems: List<GeoCountryCity> = searchEngine(items, lastWord)
        filterResults.values = resultItems
        filterResults.count = resultItems.size
        return filterResults
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        if (results != null && results.count > 0) {
            //
        } else {
            //notifyDataSetInvalidated()
        }
    }

    private fun searchEngine(listForSearch: ArrayList<GeoCountryCity>, strForSearch: String): List<GeoCountryCity> {
        val filterList = ArrayList<GeoCountryCity>()
        return if (listForSearch.isEmpty()) {
            filterList
        } else {
            for (item in listForSearch) {
                val countryAndRegion = item.description
                val city = item.name
                if (countryAndRegion.contains(strForSearch, true) || city.contains(strForSearch, true)) {
                    filterList.add(item)
                }
            }
            filterList
        }
    }
}