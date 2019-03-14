package com.task2trip.android.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import com.task2trip.android.Model.GeoCountryCity
import com.task2trip.android.Model.MockData
import com.task2trip.android.R
import com.task2trip.android.UI.CountryAndCityFilter
import com.task2trip.android.UI.Holder.CountryAndCityHolder

class CountryAndCityAdapter(val context: Context): BaseAdapter(), Filterable {
    private val items = ArrayList<GeoCountryCity>()
    private val filter = CountryAndCityFilter()

    init {
        items.addAll(MockData.getGeoLocations())
    }

    fun setItems(items: List<GeoCountryCity>) {
        this.items.clear()
        this.items.addAll(items)
        filter.setItems(this.items)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        if (view == null) {
            val inflater: LayoutInflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.item_geo_country_city, parent, false)
        }
        view?.let {
            val holder = CountryAndCityHolder(it)
            holder.setData(getItem(position))
        }
        return view
    }

    override fun getItem(position: Int): GeoCountryCity {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position * 1L
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getFilter(): Filter {
        return filter
    }
}