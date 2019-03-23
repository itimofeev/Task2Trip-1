package com.task2trip.android.UI.Widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.task2trip.android.Model.Location.GeoCountryCity
import com.task2trip.android.Model.MockData
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.CountryAndCityAdapter
import kotlinx.android.synthetic.main.view_search_location.view.*

class SearchLocationField(context: Context?, attrs: AttributeSet?) : LinearLayoutCompat(context, attrs) {

    private var callback: SearchLocationFieldCallback? = null
    private lateinit var adapter: CountryAndCityAdapter
    private var lastSelectedValue: GeoCountryCity = MockData.getEmptyGeoLocations()

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val view = LayoutInflater.from(context).inflate(R.layout.view_search_location, this)
        adapter = CountryAndCityAdapter(view.context)
        ivClose.setOnClickListener {
            clearText()
        }
        ivLocation.setOnClickListener {
            onLocationChange()
        }
        initCountryAndCityField()
    }

    private fun initCountryAndCityField() {
        etCountryAndCity.threshold = 2
        etCountryAndCity.setAdapter(adapter)
        etCountryAndCity.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                callback?.onTextLocationChanged(s.toString())
            }
        })
        etCountryAndCity.setOnItemClickListener { parent, view, position, id ->
            val selectedValue: GeoCountryCity = adapter.getItem(position)
            onTextClicked(selectedValue)
            lastSelectedValue = selectedValue
            callback?.onItemLocationChanged(selectedValue)
        }
    }

    private fun onTextClicked(selectedValue: GeoCountryCity) {
        etCountryAndCity.setText(selectedValue.description.plus(" (").plus(selectedValue.name).plus(")"))
    }

    fun getMinimumTextSearchSize(): Int {
        return etCountryAndCity.threshold
    }

    fun setMinimumTextSearchSize(size: Int) {
        etCountryAndCity.threshold = size
    }

    fun getText(): String {
        return etCountryAndCity.text.toString()
    }

    fun getItem(): GeoCountryCity {
        if (lastSelectedValue.point.radius < 1) {
            lastSelectedValue.point.radius = 1000
        }
        return lastSelectedValue
    }

    fun setHint(hintName: CharSequence) {
        tvCountryAndCity.hint = hintName
    }

    fun setSearchLocationFieldCallback(callback: SearchLocationFieldCallback) {
        this.callback = callback
    }

    fun setDataForSearch(items: List<GeoCountryCity>) {
        adapter.setItems(items)
    }

    private fun clearText() {
        etCountryAndCity.setText("")
        lastSelectedValue = MockData.getEmptyGeoLocations()
    }

    private fun onLocationChange() {
        callback?.onLocationClick()
    }
}