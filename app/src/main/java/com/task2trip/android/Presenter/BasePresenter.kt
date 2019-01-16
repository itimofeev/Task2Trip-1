package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Network.ApiMethods
import retrofit2.Call
import com.task2trip.android.Network.ResponseCallBack

open class BasePresenter() {
    private lateinit var apiMethods: ApiMethods

    constructor(context: Context) : this() {
        apiMethods = ApiMethods.getInstance(context)
    }

    protected fun getApi(): ApiMethods {
        return apiMethods
    }

    protected fun<T> Call<T>.enqueue(callback: ResponseCallBack<T>.() -> Unit) {
        val callBackKt = ResponseCallBack<T>()
        callback.invoke(callBackKt)
        this.enqueue(callBackKt)
    }
}