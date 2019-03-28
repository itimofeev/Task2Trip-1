package com.task2trip.android.UI.Fragment.Message

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.Chat.ChatMessage
import com.task2trip.android.Model.Chat.MessageScheduler
import com.task2trip.android.Presenter.ChatMessagePresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.ChatMessageDialogAdapter
import com.task2trip.android.UI.Dialog.SelectPhotoSourceDialog
import com.task2trip.android.UI.Dialog.show
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.ChatMessageView
import kotlinx.android.synthetic.main.fragment_message_chat_dialog.*
import java.util.*
import kotlin.collections.ArrayList

class MessageChatDialogFragment: BaseFragment(), ChatMessageView, ItemClickListener<ChatMessage> {
    private lateinit var presenter: ChatMessagePresenter
    private lateinit var adapter: ChatMessageDialogAdapter
    private var chatId: String = ""
    private var userOwnerId: String = ""
    private val tDelay = 8*1000L
    private val tPeriod = 12*1000L
    private var timer: Timer? = null

    override fun getArgs(args: Bundle?) {
        args?.let {
            chatId = it.getString(Constants.EXTRA_CHAT_ID, "")
            userOwnerId = it.getString(Constants.EXTRA_USER_ID, "")
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_message_chat_dialog
    }

    override fun initComponents(view: View) {
        setToolbar(toolbar)
        initPresenter(view)
        initRecycleView(view)
        initAdapter()
        ivAttachObject.setOnClickListener {
            addFileDialog()
        }
        ivSendMessage.setOnClickListener {
            sendMessage(getValidatedText(etInputField.text.toString()))
        }
    }
    
    private fun initPresenter(view: View) {
        presenter = ChatMessagePresenter(this, view.context)
        if (chatId.isNotEmpty()) {
            presenter.getMessagesFromChat(chatId)
        }
    }
    
    private fun initRecycleView(view: View) {
        rvChatDialogList.setHasFixedSize(true)
        rvChatDialogList.layoutManager = LinearLayoutManager(view.context)
    }

    private fun initAdapter() {
        adapter = ChatMessageDialogAdapter(ArrayList<ChatMessage>(), userOwnerId)
        adapter.setClickListener(this)
    }
    
    private fun addFileDialog() {
        val dialog = SelectPhotoSourceDialog()
        dialog.show(this)
    }

    override fun onStart() {
        super.onStart()
        timer = Timer()
        timer?.schedule(MessageScheduler(presenter, chatId), tDelay, tPeriod)//Старт через tDelay сек, повторять каждые tPeriod сек
    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
        timer = null
    }

    override fun onMessageList(messages: List<ChatMessage>) {
        val sortedList = ArrayList<ChatMessage>()
        sortedList.addAll(messages)
        sortedList.sortBy { it.time }
        adapter.clear()
        adapter.addItems(sortedList)
        rvChatDialogList?.adapter = adapter
    }

    private fun sendMessage(message: String) {
        if (message.isNotEmpty()) {
            presenter.sendMessageToChat(chatId, message)
        }
    }

    private fun getValidatedText(text: String): String {
        return text.trim()
    }

    override fun onMessageResult(message: ChatMessage) {
        adapter.addItem(message)
        etInputField?.setText("")
        rvChatDialogList?.scrollToPosition(adapter.itemCount)
    }

    override fun onItemClick(item: ChatMessage, position: Int) {
        //
    }

    override fun onProgress(isProgress: Boolean) {
        //TODO: Only the original thread that created a view hierarchy can touch its views.
//        if (isProgress) {
//            viewLoadAndMessage?.show()
//        } else {
//            viewLoadAndMessage?.hide()
//        }
//        viewLoadAndMessage?.setProgress(isProgress)
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

    override fun onDestroyView() {
        super.onDestroyView()
        timer = null
    }
}