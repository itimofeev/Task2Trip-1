package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Network.ApiMethods
import retrofit2.Call
import com.task2trip.android.Network.ResponseCallBack
import com.task2trip.android.View.BaseView

open class BasePresenter() {
    private lateinit var apiMethods: ApiMethods
    private var view: BaseView? = null

    constructor(context: Context) : this() {
        apiMethods = ApiMethods.getInstance(context)
    }

    constructor(view: BaseView, context: Context): this(context) {
        this.view = view
    }

    protected fun getApi(): ApiMethods {
        return apiMethods
    }

    protected fun <T> Call<T>.enqueue(callback: ResponseCallBack<T>.() -> Unit) {
        val callBackKt = if (view == null) {
            ResponseCallBack<T>()
        } else {
            ResponseCallBack<T>(view!!)
        }
        callback.invoke(callBackKt)
        this.enqueue(callBackKt)
    }
}