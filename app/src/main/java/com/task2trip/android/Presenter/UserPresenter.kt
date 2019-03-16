package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.View.UserView
import retrofit2.Call

class UserPresenter(val view: UserView, context: Context) : BasePresenter(view, context) {

    fun getUserInfo() {
        view.onProgress(true)
        val req: Call<UserImpl> = getApi().getUserInfo()
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    view.onUserInfoResult(response.body() ?: MockData.getEmptyUser())
                }
                view.onProgress(false)
            }
        }
    }
}