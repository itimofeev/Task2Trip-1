package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Common.toInt
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Offer
import com.task2trip.android.Model.OfferForSave
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
        task = args?.getParcelable<Task>(Constants.EXTRA_TASK) ?: MockData.getEmptyTask()
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_add_offer
    }

    override fun initComponents(view: View) {
        setToolbar(toolbar, true, getString(R.string.title_add_offer), true)
        initPresenter(view)
        btAddOffer.setOnClickListener {
            onOfferAddClick(etAboutOffer.text.toString(), etOfferPrice.toInt())
        }
        tvTaskPrice.text = "${task.budgetEstimate} Rub"
    }

    private fun initPresenter(view: View) {
        presenter = TaskOfferPresenter(this, view.context)
    }

    private fun onOfferAddClick(comment: String, price: Int) {
        presenter.offerSaveByTask(task.id, OfferForSave(comment, price))
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }

    override fun onSaveOfferResult(offer: Offer) {
        if (offer.id.isNotEmpty()) {
            val args = Bundle()
            with(args) {
                putBoolean(Constants.EXTRA_IS_MESSAGE, true)
                putString(Constants.EXTRA_MESSAGE_TEXT, "Вы успешно откликнулись на предложени!")
            }
            navigateTo(R.id.taskListPerformerFragment, args)
        }
    }

    override fun onOffersResult(offerList: List<Offer>) {
        //
    }

    override fun onSetOfferForUser(offer: Offer) {
        //
    }

    override fun onMyOffersResult(offers: List<Offer>) {
        //
    }
}
