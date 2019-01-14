package com.task2trip.android.Presenter

import retrofit2.Call
import com.task2trip.android.Network.ResponseCallBack

open class BasePresenter {

    protected fun<T> Call<T>.enqueue(callback: ResponseCallBack<T>.() -> Unit) {
        val callBackKt = ResponseCallBack<T>()
        callback.invoke(callBackKt)
        this.enqueue(callBackKt)
    }
}