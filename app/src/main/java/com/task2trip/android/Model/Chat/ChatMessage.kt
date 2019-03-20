package com.task2trip.android.Model.Chat

import com.task2trip.android.Model.User.UserImpl

data class ChatMessage(val id: String,
                       val user: UserImpl,
                       val value: String,
                       val time: String)