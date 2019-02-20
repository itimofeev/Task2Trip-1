package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.User.UserDataReq
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.Model.User.UserInfoResp
import com.task2trip.android.Model.User.UserLoginResp
import com.task2trip.android.View.UserView
import retrofit2.Call

class UserPresenter(val view: UserView, context: Context): BasePresenter(context) {

    fun registerUser(email: String, password: String) {
        val req: Call<UserInfoResp> = getApi().userRegister(
            UserDataReq(
                email,
                password
            )
        )
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    val client: UserInfoResp? = response.body()
                    client?.let { view.onRegisterResult(client) } ?: view.onRegisterResult(
                        UserInfoResp(
                            "",
                            ""
                        )
                    )
                } else {
                    view.onMessage("Запрос прошел, но есть ошибка ${response.code()}")
                }
                view.onProgress(false)
            }

            onFailure = { onFailure ->
                view.onMessage("Сетевая ошибка ${onFailure?.message}")
                view.onProgress(false)
            }
        }
    }

    fun userLogin(email: String, password: String) {
        val req: Call<UserLoginResp> = getApi().userLogin(UserDataReq(email, password))
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    view.onLoginResult(response.body() ?: UserLoginResp("", UserImpl()))
                } else {
                    view.onMessage("Запрос прошел, но есть ошибка ${response.code()}")
                }
                view.onProgress(false)
            }

            onFailure = { onFailure ->
                view.onMessage("Сетевая ошибка ${onFailure?.message}")
                view.onProgress(false)
            }
        }
    }

    fun userInfo() {
        val req: Call<UserInfoResp> = getApi().userInfo()
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    val client: UserInfoResp? = response.body()
                    client?.let { view.onUserInfoResult(client) } ?: view.onUserInfoResult(
                        UserInfoResp(
                            "",
                            ""
                        )
                    )
                } else {
                    view.onMessage("Запрос прошел, но есть ошибка ${response.code()}")
                }
                view.onProgress(false)
            }

            onFailure = { onFailure ->
                view.onMessage("Сетевая ошибка ${onFailure?.message}")
                view.onProgress(false)
            }
        }
    }
}