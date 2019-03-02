package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_list_performer.*

class TaskListPerformerFragment : BaseFragment() {
    private var userId = ""

    companion object {
        fun getInstance(userId: String): TaskListPerformerFragment {
            val fragment = TaskListPerformerFragment()
            val args = Bundle()
            args.putString(Constants.EXTRA_USER_ID, userId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun getArgs(args: Bundle?) {
        args?.let {
            userId = it.getString(Constants.EXTRA_USER_ID, "")
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_list_performer
    }

    override fun initComponents(view: View) {
        initYoySelect()
        initConfirmation()
        btSearch.setOnClickListener {
            navigateTo(R.id.searchFilterFragment, Bundle())
        }
    }

    private fun initYoySelect() {
        groupYouSelect.setOnClickListener {
            onYouSelectClick()
        }
        ivYouSelect.setOnClickListener {
            onYouSelectClick()
        }
        tvYouSelect.setOnClickListener {
            onYouSelectClick()
        }
        ivArrowYouSelect.setOnClickListener {
            onYouSelectClick()
        }
    }

    private fun initConfirmation() {
        groupConfirmation.setOnClickListener {
            onConfirmationClick()
        }
        ivConfirmation.setOnClickListener {
            onConfirmationClick()
        }
        tvConfirmation.setOnClickListener {
            onConfirmationClick()
        }
        ivArrowConfirmation.setOnClickListener {
            onConfirmationClick()
        }
    }

    private fun onYouSelectClick() {
        val args = Bundle()
        args.putBoolean(Constants.EXTRA_OFFER_IS_SHOW_MY, true)
        navigateTo(R.id.taskOffersFragment, args)
    }

    private fun onConfirmationClick() {
        val args = Bundle()
        args.putBoolean(Constants.EXTRA_OFFER_IS_SHOW_MY, true)
        navigateTo(R.id.taskOffersFragment, args)
    }
}