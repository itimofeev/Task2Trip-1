ackage com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.R

class ChatMessageDialogAdapter(items: List<TaskCategory>):
    BaseListAdapter<ChatMessageDialogHolder, TaskCategory>(items) {
    private var listener: ItemClickListener<TaskCategory>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_task_add_category
    }

    override fun returnViewHolder(view: View): ChatMessageDialogHolder {
        return ChatMessageDialogHolder(view)
    }

    override fun onItemClicked(item: TaskCategory, position: Int) {
        this.listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<TaskCategory>?) {
        this.listener = listener
    }
}
