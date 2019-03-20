package com.task2trip.android.UI.Fragment.Message

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Model.Chat.Chat
import com.task2trip.android.Model.Chat.ChatList
import com.task2trip.android.Presenter.ChatPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.ChatsAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.ChatView
import kotlinx.android.synthetic.main.fragment_message_chat_list.*

class MessageChatListFragment : BaseFragment(), ChatView, ItemClickListener<Chat> {
    private lateinit var presenter: ChatPresenter

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_message_chat_list
    }

    override fun initComponents(view: View) {
        initPresenter(view)
        initRecycleView(view)
    }
    
    private fun initPresenter(view: View) {
        presenter = ChatPresenter(this, view.context)
        presenter.getChats()
    }
    
    private fun initRecycleView(view: View) {
        rvChatList.setHasFixedSize(true)
        rvChatList.layoutManager = LinearLayoutManager(view.context)
    }

    override fun onChatListResult(chats: ChatList) {
        if (chats.total > 0) {
            val adapter = ChatsAdapter(chats.payload)
            adapter.setClickListener(this)
            rvChatList.adapter = adapter
        }
    }

    override fun onChatCreateResult(chat: Chat) {
        //
    }

    override fun onItemClick(item: Chat, position: Int) {
        navigateTo(R.id.messageChatDialogFragment, Bundle())
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
