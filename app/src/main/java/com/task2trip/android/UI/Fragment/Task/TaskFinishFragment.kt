package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.Offer
import com.task2trip.android.Model.Task.TaskStatus
import com.task2trip.android.Model.Task.TaskStatusAndRating
import com.task2trip.android.Presenter.TaskChangeStatusPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.View.TaskChangeStatusView
import com.task2trip.widgetlibrary.LoadingAndMessage
import com.task2trip.widgetlibrary.MessageFinishShowCallback
import kotlinx.android.synthetic.main.fragment_task_finish.*

class TaskFinishFragment : BaseFragment(), TaskChangeStatusView, MessageFinishShowCallback {
    private lateinit var presenter: TaskChangeStatusPresenter
    private var offerId = ""
    private var taskId = ""

    override fun getArgs(args: Bundle?) {
        args?.apply {
            taskId = getString(Constants.EXTRA_TASK_ID, "") ?: ""
            offerId = getString(Constants.EXTRA_OFFER_ID, "") ?: ""
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_finish
    }

    override fun initComponents(view: View) {
        setToolbar(toolbar)
        presenter = TaskChangeStatusPresenter(this, view.context)
        viewLoadAndMessage.setMessageCloseCallback(this)
        btYes.setOnClickListener {
            presenter.setTaskWithOfferFinished(taskId, offerId,
                TaskStatusAndRating(TaskStatus.FINISHED.name.toLowerCase(), (ratingBar.rating * 20f).toInt()))
        }
        btNo.setOnClickListener {
            navigateToBack()
        }
    }

    override fun onTaskStatusResult(offer: Offer) {
        viewLoadAndMessage?.setMessage("Задача закрыта, а рейтинг проставлен!", LoadingAndMessage.SHOW_MIDDLE)
    }

    override fun onCloseMessage() {
        navigateTo(R.id.taskListPerformerFragment, Bundle())
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