package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.User.ProfileImpl
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.View.UserProfileView
import retrofit2.Call

class UserProfilePresenter(val view: UserProfileView, context: Context): BasePresenter(view, context) {

    fun updateUserProfile(profile: ProfileImpl) {
        view.onProgress(true)
        val req: Call<ProfileImpl> = getApi().updateUserProfile(profile)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onUserProfileUpdateResult(response.body() ?: MockData.getEmptyProfile())
                } else {
                    view.onMessage("Запрос прошел, но есть ошибка ${response.code()}")
                }
            }
        }
    }
}