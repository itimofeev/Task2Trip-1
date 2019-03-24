package com.task2trip.android.View

import com.task2trip.android.Model.Notification.NotificationList

interface NotificationView: BaseView {
    fun onNotificationListResult(result: NotificationList)
    fun onNotificationReaded()
}