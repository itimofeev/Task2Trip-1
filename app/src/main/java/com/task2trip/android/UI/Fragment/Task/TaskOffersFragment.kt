package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.Offer
import com.task2trip.android.Presenter.TaskOfferPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TaskOfferListAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.TaskOfferView
import kotlinx.android.synthetic.main.fragment_task_offers.*

class TaskOffersFragment : BaseFragment(), TaskOfferView, ItemClickListener<Offer> {
    private lateinit var presenter: TaskOfferPresenter
    private var taskId = ""

    companion object {
        fun getInstance(taskId: String): TaskOffersFragment {
            val fragment = TaskOffersFragment()
            val args = Bundle()
            args.putString(Constants.EXTRA_TASK_ID, taskId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun getArgs(args: Bundle?) {
        args?.let {
            taskId = it.getString(Constants.EXTRA_TASK_ID, "")
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_offers
    }

    override fun initComponents(view: View) {
        tvEmptyMessage.visibility = View.GONE
        initRecycleView(view)
        initPresenter(view)
    }

    private fun initRecycleView(view: View) {
        rvOffersList.setHasFixedSize(true)
        rvOffersList.layoutManager = LinearLayoutManager(view.context)
    }

    private fun initPresenter(view: View) {
        presenter = TaskOfferPresenter(this, view.context)
        presenter.getOffersByTaskId(taskId)
    }

    override fun onSaveOfferResult(offer: Offer) {
        //
    }

    override fun onOffersResult(offerList: List<Offer>) {
        if (offerList.isEmpty()) {
            tvEmptyMessage.visibility = View.VISIBLE
            rvOffersList.visibility = View.GONE
        } else {
            tvEmptyMessage.visibility = View.GONE
            rvOffersList.visibility = View.VISIBLE
        }
        val adapter = TaskOfferListAdapter(offerList)
        adapter.setClickListener(this)
        rvOffersList.adapter = adapter
    }

    override fun onItemClick(item: Offer, position: Int) {
        val args = Bundle()
        with(args) {
            putParcelable(Constants.EXTRA_OFFER, item)
            putString(Constants.EXTRA_TASK_ID, taskId)
        }
        navigateTo(R.id.offerDetailFragment, args)
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}