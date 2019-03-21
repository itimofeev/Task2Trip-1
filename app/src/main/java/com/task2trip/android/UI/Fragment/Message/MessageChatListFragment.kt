package com.task2trip.android.UI.Fragment.Message

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.Chat.Chat
import com.task2trip.android.Model.Chat.ChatCreateForUser
import com.task2trip.android.Model.Chat.ChatList
import com.task2trip.android.Model.MockData
import com.task2trip.android.Presenter.ChatPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.ChatsAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.ChatView
import kotlinx.android.synthetic.main.fragment_message_chat_list.*

class MessageChatListFragment : BaseFragment(), ChatView, ItemClickListener<Chat> {
    private var userLocalId: String = ""
    private var userId: String = ""
    private var isGotoMessages: Boolean = false
    private lateinit var presenter: ChatPresenter

    companion object {
        fun getInstance(userLocalId: String, userId: String, isGotoMessages: Boolean): MessageChatListFragment {
            val fragment = MessageChatListFragment()
            val args = Bundle()
            args.putString(Constants.EXTRA_USER_LOCAL_ID, userLocalId)
            args.putString(Constants.EXTRA_USER_ID, userId)
            args.putBoolean(Constants.EXTRA_DIALOG_IS_GOTO_MESSAGE, isGotoMessages)
            fragment.arguments = args
            return fragment
        }
    }

    override fun getArgs(args: Bundle?) {
        args?.let {
            userLocalId = it.getString(Constants.EXTRA_USER_LOCAL_ID, "")
            userId = it.getString(Constants.EXTRA_USER_ID, "")
            isGotoMessages = it.getBoolean(Constants.EXTRA_DIALOG_IS_GOTO_MESSAGE, false)
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
            if (isGotoMessages) {
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
            if (item.user.getId() == userLocalId) {
                return item
            }
        }
        return MockData.getEmptyChat()
    }

    override fun onChatCreateResult(chat: Chat) {
        navigateToChat(chat)
    }

    override fun onChatMarkAsReadResult() {
        //
    }

    private fun navigateToChat(chat: Chat) {
        presenter.markChatAsRead(chat.id)
        val args = Bundle()
        args.putString(Constants.EXTRA_CHAT_ID, chat.id)
        args.putString(Constants.EXTRA_USER_ID, userId)
        navigateTo(R.id.messageChatDialogFragment, args)
    }

    override fun onItemClick(item: Chat, position: Int) {
        navigateToChat(item)
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
