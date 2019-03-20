package com.task2trip.android.UI.Fragment.Message

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.Chat.Chat
import com.task2trip.android.Model.Chat.ChatCreateForUser
import com.task2trip.android.Model.Chat.ChatList
import com.task2trip.android.Model.Chat.ChatMessage
import com.task2trip.android.Model.MockData
import com.task2trip.android.Presenter.ChatPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.ChatsAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.ChatView
import kotlinx.android.synthetic.main.fragment_message_chat_list.*

class MessageChatListFragment : BaseFragment(), ChatView, ItemClickListener<Chat> {
    private var userId: String = ""
    private lateinit var presenter: ChatPresenter

    companion object {
        fun getInstance(userId: String): MessageChatListFragment {
            val fragment = MessageChatListFragment()
            val args = Bundle()
            args.putString(Constants.EXTRA_USER_ID, userId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun getArgs(args: Bundle?) {
        args?.let {
            userId = it.getString(Constants.EXTRA_USER_ID, "")
        }
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
            // Если указан пользователь, то должны начать чат с ним, если с ним чат есть
            if (userId.isNotEmpty()) {
                val chat = getChatId(chats.payload)
                if (chat.id.isEmpty()) {
                    presenter.createChat(ChatCreateForUser(userId))
                } else {
                    navigateToChat(chat)
                }
            }
        } else {
            presenter.createChat(ChatCreateForUser(userId))
        }
    }

    /**
     * Если ли есть чат
     */
    private fun getChatId(items: List<Chat>): Chat {
        for (item in items) {
            if (item.user.getId() == userId) {
                return item
            }
        }
        return MockData.getEmptyChat()
    }

    override fun onChatCreateResult(chat: Chat) {
        navigateToChat(chat)
    }

    override fun onChatMarkAsReadResult(message: ChatMessage) {
        //
    }

    private fun navigateToChat(chat: Chat) {
        presenter.markChatAsRead(chat.id)
        val args = Bundle()
        args.putString(Constants.EXTRA_CHAT_ID, chat.id)
        args.putString(Constants.EXTRA_CHAT_CLIENT_TAG, chat.lastMessage.clientTag)
        navigateTo(R.id.messageChatDialogFragment, args)
    }

    override fun onItemClick(item: Chat, position: Int) {
        navigateToChat(item)
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
