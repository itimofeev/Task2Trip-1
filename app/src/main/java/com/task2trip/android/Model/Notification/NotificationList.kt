package com.task2trip.android.Model.Notification

class NotificationList(totalUnread: Int, notifications: List<NotificationData>) {
    val notifications: List<NotificationData> = notifications
        get() = field ?: ArrayList<NotificationData>()

    val totalUnread: Int = totalUnread
        get() = field ?: 0
}