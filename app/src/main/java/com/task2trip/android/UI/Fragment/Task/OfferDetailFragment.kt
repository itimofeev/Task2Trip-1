package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.ImageLoader.ImageLoader
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Offer
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_offer_detail.*

class OfferDetailFragment : BaseFragment() {
    private var taskId: String = ""
    private var offer: Offer = MockData.getEmptyOffer()

    companion object {
        fun getInstance(offer: Offer, taskId: String): OfferDetailFragment {
            val fragment = OfferDetailFragment()
            val args = Bundle()
            with(args) {
                putParcelable(Constants.EXTRA_OFFER, offer)
                putString(Constants.EXTRA_TASK_ID, taskId)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun getArgs(args: Bundle?) {
        args?.let {
            taskId = it.getString(Constants.EXTRA_TASK_ID, "")
            offer = it.getParcelable(Constants.EXTRA_OFFER) ?: MockData.getEmptyOffer()
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_offer_detail
    }

    override fun initComponents(view: View) {
        ImageLoader("offer.url", ivUserPhoto)
        tvPerformerName.text = offer.user.getName()
        tvTaskPrice.text = "${offer.price} Rub"
        tvPaymentType.text = "Оплата наличными"
        tvOfferDescription.text = offer.comment
        btSetMyLocal.setOnClickListener {
            onSetMyLocal()
        }
        btSendMessage.setOnClickListener {
            onSendMessage()
        }
    }

    private fun onSetMyLocal() {
        val args = Bundle()
        args.putBoolean(Constants.EXTRA_IS_MESSAGE, true)
        args.putString(Constants.EXTRA_MESSAGE_TEXT, "Пользователь ${offer.user.getName()} выбран исполнителем!")
        navigateTo(R.id.taskListPerformerFragment, args)
    }

    private fun onSendMessage() {
        val args = Bundle()
        args.putParcelable(Constants.EXTRA_USER, offer.user)
        navigateTo(R.id.messageFragment, args)
    }
}
