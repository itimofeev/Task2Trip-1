package com.task2trip.widgetlibrary

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.view_loading_and_message.view.*

class LoadingAndMessage(context: Context?, attrs: AttributeSet?) : LinearLayoutCompat(context, attrs) {
    private var messageCallback: MessageFinishShowCallback? = null

    companion object {
        const val SHOW_SHORT: Long = 1000L
        const val SHOW_MIDDLE: Long = 2500L
        const val SHOW_LONG: Long = 5000L
    }

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.view_loading_and_message, this)
        pbLoading.visibility = View.GONE
        tvMessage.text = ""
    }

    fun setMessageCloseCallback(messageCallback: MessageFinishShowCallback) {
        this.messageCallback = messageCallback
    }

    fun setProgress(isProgress: Boolean) {
        if (isProgress) {
            pbLoading.visibility = View.VISIBLE
        } else {
            pbLoading.visibility = View.GONE
        }
    }

    fun setMessage(message: String, showTime: Long) {
        setMessage(message)
        if (showTime > 0 && showTime < 30 * 1000L) {
            this.postDelayed({ onMessageFinishShow() }, showTime)
        }
    }

    private fun onMessageFinishShow() {
        hide()
        messageCallback?.onCloseMessage()
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