package com.task2trip.android

import android.os.Bundle
import android.view.View
import com.task2trip.android.Model.Chat.ChatMessage
import com.task2trip.android.Presenter.ChatMessagePresenter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.View.ChatMessageView

class MessageChatDialogFragment : BaseFragment(), ChatMessageView {
    private lateinit var presenter: ChatMessagePresenter

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_message_chat_dialog
    }

    override fun initComponents(view: View) {
        presenter = ChatMessagePresenter(this, view.context)
        presenter.getMessagesFromChat("", "", 0)
    }

    override fun onMessageResult(message: ChatMessage) {
        //
    }

    override fun onMessageList(messages: List<ChatMessage>) {
        //
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
