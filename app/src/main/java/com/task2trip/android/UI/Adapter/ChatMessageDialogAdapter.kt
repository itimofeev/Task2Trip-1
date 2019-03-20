ackage com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.R

class ChatMessageDialogAdapter(items: List<ChatMessage>):
    BaseListAdapter<ChatMessageDialogHolder, ChatMessage>(items) {
    private var listener: ItemClickListener<ChatMessage>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_task_add_category
    }

    override fun returnViewHolder(view: View): ChatMessageDialogHolder {
        return ChatMessageDialogHolder(view)
    }

    override fun onItemClicked(item: ChatMessage, position: Int) {
        this.listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<ChatMessage>?) {
        this.listener = listener
    }
}
