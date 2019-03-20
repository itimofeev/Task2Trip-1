package com.task2trip.android.View

import com.task2trip.android.Model.Chat.Chat
import com.task2trip.android.Model.Chat.ChatList

interface ChatView: BaseView {
    fun onChatListResult(chats: ChatList)
    fun onChatCreateResult(chat: Chat)
}