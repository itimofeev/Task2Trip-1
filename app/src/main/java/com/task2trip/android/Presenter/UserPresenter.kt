package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.User.*
import com.task2trip.android.View.UserView
import retrofit2.Call

class UserPresenter(val view: UserView, context: Context): BasePresenter(view, context) {

    fun registerUser(email: String, password: String, name: String, locale: String = "ru") {
        val req: Call<Void> = getApi().userRegister(UserSignUpReq(email, password, locale, name))
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    view.onRegisterResult(response.body())
                }
                view.onProgress(false)
            }
        }
    }

    fun userLogin(email: String, password: String) {
        val req: Call<UserLoginResp> = getApi().userLogin(UserLoginReq(email, password))
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    view.onLoginResult(response.body() ?: MockData.getEmptyUserLoginResp())
                }
                view.onProgress(false)
            }
        }
    }

    fun userInfo() {
        val req: Call<UserInfoResp> = getApi().userInfo()
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    view.onUserInfoResult(response.body() ?: MockData.getEmptyUserInfoResp())
                }
                view.onProgress(false)
            }
        }
    }
}