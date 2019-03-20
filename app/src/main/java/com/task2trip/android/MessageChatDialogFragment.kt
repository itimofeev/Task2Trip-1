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
        initPresenter()
        initRecycleView()
        ivAttachObject.setOnClickListener {
            addFileDialog()
        }
        ivSendMessage.setOnClickListener {
            sendMessage(getValidatedText(etInputField.getText().toString()))
        }
    }
    
    private fun initPresenter() {
        presenter = ChatMessagePresenter(this, view.context)
        presenter.getMessagesFromChat("", "", 0)
    }
    
    private fun initRecycleView(view: View) {
        rvChatDialogList.setHasFixedSize(true)
        rvChatDialogList.layoutManager = LinearLayoutManager(view.context)
    }
    
    private fun addFileDialog() {
        //
    }
    
    private fun sendMessage(message: String) {
        if (message.isNotEmpty()) {
            presenter.sendMessageToChat("", message)
        }
    }
    
    private fun getValidatedText(text: String): String {
        return text.trim()
    }

    override fun onMessageResult(message: ChatMessage) {
        //
    }

    override fun onMessageList(messages: List<ChatMessage>) {
        val adapter = ChatMessageDialogAdapter(categoryList)
        adapter.setClickListener(this)
        rvChatDialogList.adapter = adapter
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
