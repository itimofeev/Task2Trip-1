package com.task2trip.android.View

interface BaseView {
    fun onError(message: String)
    fun onProgress(isProgress: Boolean)
}