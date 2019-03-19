package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.View.UserView
import okhttp3.MediaType
import okhttp3.MultipartBody
import retrofit2.Call
import okhttp3.RequestBody
import java.io.File
import java.util.*

class UserPresenter(val view: UserView, context: Context) : BasePresenter(view, context) {

    fun getUserInfo() {
        view.onProgress(true)
        val req: Call<UserImpl> = getApi().getUserInfo()
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onMySelfInfoResult(response.body() ?: MockData.getEmptyUser())
                }
            }
        }
    }

    fun saveImageAvatar(file: File) {
        view.onProgress(true)
        val postfix = Calendar.getInstance().timeInMillis.toString()
        val avatarFileName = "avatar$postfix.jpg"
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val body = MultipartBody.Part.createFormData("profileImage", avatarFileName, requestFile)
        val req: Call<String> = getApi().saveUserImageAvatar(body)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onUploadImageAvatarResult(true)
                } else {
                    view.onUploadImageAvatarResult(false)
                }
            }
        }
    }

    fun getProfileById(profileId: String) {
        view.onProgress(true)
        val req: Call<UserImpl> = getApi().getUserProfileById(profileId)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onUserResult(response.body() ?: MockData.getEmptyUser())
                } else {
                    view.onMessage("Запрос прошел, но есть ошибка ${response.code()}")
                }
            }
        }
    }
}