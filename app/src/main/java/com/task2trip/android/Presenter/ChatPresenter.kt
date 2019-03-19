package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.Chat.Chat
import com.task2trip.android.Model.Chat.ChatCreateForUser
import com.task2trip.android.Model.Chat.ChatList
import com.task2trip.android.Model.MockData
import com.task2trip.android.View.ChatView
import retrofit2.Call

class ChatPresenter(val view: ChatView, context: Context) : BasePresenter(view, context) {

    fun getChats(limit: Int? = null, skip: Int? = null) {
        view.onProgress(true)
        val req: Call<ChatList> = getApi().getChats(limit, skip)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onChatListResult(response.body() ?: MockData.getEmptyChatList())
                }
            }
        }
    }

    fun createChat(userId: ChatCreateForUser) {
        view.onProgress(true)
        val req: Call<Chat> = getApi().createChat(userId)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onChatCreateResult(response.body() ?: MockData.getEmptyChat())
                }
            }
        }
    }
}