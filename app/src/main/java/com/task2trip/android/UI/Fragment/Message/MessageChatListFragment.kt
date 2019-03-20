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
        initPresenter()
        initRecycleView()
    }
    
    private fun initPresenter() {   
        presenter = ChatPresenter(this, view.context)
        presenter.getChats()
    }
    
    private fun initRecycleView(view: View) {
        rvChatList.setHasFixedSize(true)
        rvChatList.layoutManager = LinearLayoutManager(view.context)
    }

    override fun onChatListResult(chats: ChatList) {
        val adapter = ChatsAdapter(chats.playloads)
        adapter.setClickListener(this)
        rvChatList.adapter = adapter
    }

    override fun onChatCreateResult(chat: Chat) {
        //
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
