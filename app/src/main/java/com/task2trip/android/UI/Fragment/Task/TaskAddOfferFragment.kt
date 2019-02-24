package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Offer
import com.task2trip.android.Model.Task.Task
import com.task2trip.android.Presenter.TaskOfferPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.View.TaskOfferView
import kotlinx.android.synthetic.main.fragment_task_add_offer.*

class TaskAddOfferFragment : BaseFragment(), TaskOfferView {
    private var task: Task = MockData.getEmptyTask()
    private lateinit var presenter: TaskOfferPresenter

    override fun getArgs(args: Bundle?) {
        task = args?.getParcelable<Task>(Constants.EXTRA_TASK) ?: com.task2trip.android.Model.MockData.getEmptyTask()
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_add_offer
    }

    override fun initComponents(view: View) {
        initPresenter(view)
        btAddOffer.setOnClickListener {
            onOfferAddClick()
        }
        tvTaskPrice.text = "${task.budgetEstimate} Rub"
    }

    private fun initPresenter(view: View) {
        presenter = TaskOfferPresenter(this, view.context)
    }

    private fun onOfferAddClick() {
        presenter.offerSaveByTask(task.id)
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }

    override fun onSaveOfferResult(offer: Offer) {
        //
    }
}
