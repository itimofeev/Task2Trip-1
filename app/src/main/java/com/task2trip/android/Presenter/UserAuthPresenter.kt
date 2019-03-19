package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.User.UserLoginReq
import com.task2trip.android.Model.User.UserLoginResp
import com.task2trip.android.Model.User.UserSignUpReq
import com.task2trip.android.View.UserAuthView
import retrofit2.Call

class UserAuthPresenter(val view: UserAuthView, context: Context): BasePresenter(view, context) {

    fun registerUser(email: String, password: String, name: String, locale: String = "ru") {
        val req: Call<Void> = getApi().userRegister(UserSignUpReq(email, password, locale, name))
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onRegisterResult(response.body())
                }
            }
        }
    }

    fun userLogin(email: String, password: String) {
        val req: Call<UserLoginResp> = getApi().userLogin(UserLoginReq(email, password))
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onLoginResult(response.body() ?: MockData.getEmptyUserLoginResp())
                }
            }
        }
    }
}