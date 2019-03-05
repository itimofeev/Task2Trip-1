package com.task2trip.widgetlibrary

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.view_loading_and_message.view.*

class LoadingAndMessage(context: Context?, attrs: AttributeSet?) : LinearLayoutCompat(context, attrs) {

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.view_loading_and_message, this)
        pbLoading.visibility = View.GONE
        tvMessage.text = ""
    }

    fun setProgress(isProgress: Boolean) {
        if (isProgress) {
            pbLoading.visibility = View.VISIBLE
        } else {
            pbLoading.visibility = View.GONE
        }
    }

    fun setMessage(message: String, hideMessage: Boolean) {
        setMessage(message)
        if (hideMessage) {
            this.visibility = View.GONE
        }
    }

    fun setMessage(message: String) {
        pbLoading.visibility = View.GONE
        tvMessage.visibility = View.VISIBLE
        tvMessage.text = message
    }

    fun hide() {
        this.visibility = View.GONE
    }

    fun show() {
        this.visibility = View.VISIBLE
    }
}