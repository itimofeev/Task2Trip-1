package com.task2trip.android.Model.Chat

class ChatList(payload: List<Chat>, total: Int) {
    val payload: List<Chat> = payload
    get() = field ?: ArrayList<Chat>()

    val total: Int = total
    get() = field ?: 0
}