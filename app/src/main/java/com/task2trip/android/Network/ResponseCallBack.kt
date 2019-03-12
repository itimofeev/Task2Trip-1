package com.task2trip.android.Network

import com.task2trip.android.View.BaseView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseCallBack<T>(): Callback<T> {
    var onResponse: ((Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable?) -> Unit)? = null
    private var view: BaseView? = null

    constructor(view: BaseView): this() {
        this.view = view
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        view?.let {
            it.onMessage("Сетевая ошибка ${t.message}")
            it.onProgress(false)
        }
        onFailure?.invoke(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.code() !in 200..299) {
            view?.onMessage(response.message())
        }
        onResponse?.invoke(response)
    }
}