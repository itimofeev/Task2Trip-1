package com.task2trip.android.Model.Notification

data class NotificationData(val id: String,
                            val type: String,
                            val title: String,
                            val subtitle: String,
                            val createTime: String,
                            val taskId: String)