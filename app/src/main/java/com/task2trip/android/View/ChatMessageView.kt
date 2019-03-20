package com.task2trip.android.View

import com.task2trip.android.Model.Chat.ChatMessage

interface ChatMessageView: BaseView {
    fun onMessageResult(message: ChatMessage)
    fun onMessageList(messages: List<ChatMessage>)
}