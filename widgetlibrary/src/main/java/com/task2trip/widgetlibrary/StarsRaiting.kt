package com.task2trip.widgetlibrary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat

class StarsRaiting(context: Context?, attrs: AttributeSet?) : LinearLayoutCompat(context, attrs) {
    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.view_stars_raiting, this)
    }

    fun setRaitingValue(value: Int) {
        //
    }

    fun setRaitingValue(value: Float) {
        //
    }

    fun clearRaiting() {
        //
    }
}