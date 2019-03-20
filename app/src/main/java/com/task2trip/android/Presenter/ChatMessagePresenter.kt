package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.Chat.ChatMessage
import com.task2trip.android.Model.Chat.ChatMessageForSend
import com.task2trip.android.Model.MockData
import com.task2trip.android.View.ChatMessageView
import retrofit2.Call

class ChatMessagePresenter(val view: ChatMessageView, context: Context) : BasePresenter(view, context) {

    fun sendMessageToChat(chatId: String, message: ChatMessageForSend) {
        view.onProgress(true)
        val req: Call<ChatMessage> = getApi().sendMessageToChat(chatId, message)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onMessageResult(response.body() ?: MockData.getEmptyChatMessage())
                }
            }
        }
    }

    fun getMessagesFromChat(chatId: String, beforeTime: String, limit: Int) {
        view.onProgress(true)
        val req: Call<List<ChatMessage>> = getApi().getMessagesFromChat(chatId, beforeTime, limit)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onMessageList(response.body() ?: MockData.getEmptyChatMessageList())
                }
            }
        }
    }
}