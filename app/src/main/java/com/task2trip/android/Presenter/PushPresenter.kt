package com.task2trip.android.Presenter

import android.content.Context
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.task2trip.android.Model.Notification.UserDeviceToken
import com.task2trip.android.View.BaseView
import retrofit2.Call

class PushPresenter(view: BaseView, context: Context) : BasePresenter(view, context) {

    fun registerPushToken() {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            val token: String = task.result?.token ?: ""
            val req: Call<Void> = getApi().registerPushToken(UserDeviceToken("android", token))
            req.enqueue {
                onResponse = { response ->
                    //
                }
            }
        })
    }

    fun unRegisterPushToken(token: String) {
        val req: Call<Void> = getApi().deletePushToken(token)
        req.enqueue {
            onResponse = { response ->
                //
            }
        }
    }
}