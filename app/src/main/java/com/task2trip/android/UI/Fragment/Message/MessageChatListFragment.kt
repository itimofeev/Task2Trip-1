package com.task2trip.android.UI.Fragment.Message

import android.os.Bundle
import android.view.View
import com.task2trip.android.Model.Chat.Chat
import com.task2trip.android.Model.Chat.ChatList
import com.task2trip.android.Presenter.ChatPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.View.ChatView

class MessageChatListFragment : BaseFragment(), ChatView {
    private lateinit var presenter: ChatPresenter

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_message_chat_list
    }

    override fun initComponents(view: View) {
        presenter = ChatPresenter(this, view.context)
        presenter.getChats()
    }

    override fun onChatListResult(chats: ChatList) {
        //
    }

    override fun onChatCreateResult(chat: Chat) {
        //
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
