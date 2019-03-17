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

class UserPresenter(val view: UserView, context: Context) : BasePresenter(view, context) {

    fun getUserInfo() {
        view.onProgress(true)
        val req: Call<UserImpl> = getApi().getUserInfo()
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onUserInfoResult(response.body() ?: MockData.getEmptyUser())
                }
            }
        }
    }

    fun saveImageAvatar(file: File) {
        view.onProgress(true)
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val body = MultipartBody.Part.createFormData("image", file.name, requestFile)
        val req: Call<String> = getApi().saveUserImageAvatar(body)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onUploadImageAvatarResult()
                }
            }
        }
    }
}