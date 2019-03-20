package com.task2trip.android.UI.Fragment.Message

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.Chat.ChatMessage
import com.task2trip.android.Model.Chat.ChatMessageForSend
import com.task2trip.android.Presenter.ChatMessagePresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.ChatMessageDialogAdapter
import com.task2trip.android.UI.Dialog.SelectPhotoSourceDialog
import com.task2trip.android.UI.Dialog.show
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.ChatMessageView
import kotlinx.android.synthetic.main.fragment_message_chat_dialog.*

class MessageChatDialogFragment: BaseFragment(), ChatMessageView, ItemClickListener<ChatMessage> {
    private lateinit var presenter: ChatMessagePresenter

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_message_chat_dialog
    }

    override fun initComponents(view: View) {
        initPresenter(view)
        initRecycleView(view)
        ivAttachObject.setOnClickListener {
            addFileDialog()
        }
        ivSendMessage.setOnClickListener {
            sendMessage(getValidatedText(etInputField.getText().toString()))
        }
    }
    
    private fun initPresenter(view: View) {
        presenter = ChatMessagePresenter(this, view.context)
        presenter.getMessagesFromChat("", "", 0)
    }
    
    private fun initRecycleView(view: View) {
        rvChatDialogList.setHasFixedSize(true)
        rvChatDialogList.layoutManager = LinearLayoutManager(view.context)
    }
    
    private fun addFileDialog() {
        val dialog = SelectPhotoSourceDialog()
        dialog.show(this)
    }
    
    private fun sendMessage(message: String) {
        if (message.isNotEmpty()) {
            presenter.sendMessageToChat("", ChatMessageForSend(message))
        }
    }
    
    private fun getValidatedText(text: String): String {
        return text.trim()
    }

    override fun onMessageResult(message: ChatMessage) {
        //
    }

    override fun onMessageList(messages: List<ChatMessage>) {
        val adapter = ChatMessageDialogAdapter(messages)
        adapter.setClickListener(this)
        rvChatDialogList.adapter = adapter
    }

    override fun onItemClick(item: ChatMessage, position: Int) {
        //
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            Constants.REQUEST_DIALOG_CAMERA_SOURCE -> {
                if (resultCode == Activity.RESULT_OK) {
                    //
                }
            }
            Constants.REQUEST_CAMERA_PHOTO -> {
                if (resultCode == Activity.RESULT_OK) {
                    //
                }
            }
            Constants.REQUEST_STORAGE_BROWSER -> {
                if (resultCode == Activity.RESULT_OK) {
                    //
                }
            }
        }
    }
}
