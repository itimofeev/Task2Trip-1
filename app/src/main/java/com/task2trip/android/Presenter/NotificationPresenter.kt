package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Notification.NotificationList
import com.task2trip.android.View.NotificationView
import retrofit2.Call

class NotificationPresenter(val view: NotificationView, context: Context): BasePresenter(view, context) {

    fun getNotifications(limit: Int? = null, skip: Int? = null) {
        view.onProgress(true)
        val req: Call<NotificationList> = getApi().getNotifications()
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onNotificationListResult(response.body() ?: MockData.getEmptyNotificationList())
                }
            }
        }
    }

    fun markNotificationAsRead(notificationId: String) {
        view.onProgress(true)
        val req: Call<Void> = getApi().markNotificationAsRead(notificationId)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onNotificationReaded()
                }
            }
        }
    }
}