package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.Model.User.UserLevelUp
import com.task2trip.android.View.UserProfileLevelUpView
import retrofit2.Call

class UserProfileLevelUpPresenter(val view: UserProfileLevelUpView, context: Context): BasePresenter(view, context) {

    fun setLevelUpUserProfile(role: UserLevelUp) {
        view.onProgress(true)
        val req: Call<UserImpl> = getApi().updateUserRoleLocal(role)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onUserLevelUpResult(response.body() ?: MockData.getEmptyUser())
                } else {
                    view.onMessage("Запрос прошел, но есть ошибка ${response.code()}")
                }
            }
        }
    }
}