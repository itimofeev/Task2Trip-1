package com.task2trip.android.UI.Fragment.Message

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.ExternalNavigation
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Notification.NotificationData
import com.task2trip.android.Model.Notification.NotificationList
import com.task2trip.android.Presenter.NotificationPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.NotificationAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.NotificationView
import kotlinx.android.synthetic.main.fragment_message_notification.*

class MessageNotificationFragment: BaseFragment(), NotificationView, ItemClickListener<NotificationData> {
    private lateinit var presenter: NotificationPresenter

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_message_notification
    }

    override fun initComponents(view: View) {
        initPresenter(view)
        initRecycleView(view)
    }

    private fun initPresenter(view: View) {
        presenter = NotificationPresenter(this, view.context)
        presenter.getNotifications()
    }

    private fun initRecycleView(view: View) {
        rvNotification.setHasFixedSize(true)
        rvNotification.layoutManager = LinearLayoutManager(view.context)
    }

    override fun onNotificationListResult(result: NotificationList) {
//        var lst = ArrayList<NotificationData>()
//        if (result.notifications.isEmpty()) {
//            lst = MockData.getNotificationList()
//        } else {
//            lst = result.notifications.toCollection(lst)
//        }
        val adapter = NotificationAdapter(result.notifications)
        adapter.setClickListener(this)
        rvNotification?.adapter = adapter
        if (result.notifications.isEmpty()) {
            viewLoadAndMessage?.let {
                it.show()
                it.setMessage("У вас пока нет уведомлений")
            }
        }
    }

    override fun onNotificationReaded() {
        //
    }

    override fun onItemClick(item: NotificationData, position: Int) {
        presenter.markNotificationAsRead(item.id)
        val args = Bundle()
        val navigationList = ArrayList<ExternalNavigation>()
        navigationList.add(ExternalNavigation(R.id.taskAddParamsFragment, Bundle()))
        navigationList.add(ExternalNavigation(R.id.taskAddParamsFragment, Bundle()))
        navigationList.add(ExternalNavigation(R.id.taskAddParamsFragment, Bundle()))

        args.putParcelableArrayList(Constants.EXTRA_NAVIGATION_LIST, navigationList)
        args.putInt(Constants.EXTRA_TASK_CATEGORY_SELECTED_POSITION, position)
        navigateTo(Constants.EXTRA_NAVIGATION_ID, args)
    }

    override fun onProgress(isProgress: Boolean) {
        if (isProgress) {
            viewLoadAndMessage?.show()
        } else {
            viewLoadAndMessage?.hide()
        }
        viewLoadAndMessage?.setProgress(isProgress)
    }
}
