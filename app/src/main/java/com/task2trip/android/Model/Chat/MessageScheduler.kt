package com.task2trip.android.Model.Chat

import com.task2trip.android.Presenter.ChatMessagePresenter
import java.util.*

class MessageScheduler(private val presenter: ChatMessagePresenter?, private val chatId: String): TimerTask() {

    override fun run() {
        presenter?.getMessagesFromChat(chatId)
    }
}