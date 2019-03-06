package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.ImageLoader.ImageLoader
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Offer
import com.task2trip.android.Presenter.TaskOfferPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.View.TaskOfferView
import kotlinx.android.synthetic.main.fragment_offer_detail.*

class OfferDetailFragment : BaseFragment(), TaskOfferView {
    private lateinit var presenter: TaskOfferPresenter
    private var taskId: String = ""
    private var offer: Offer = MockData.getEmptyOffer()
    private var isMyOffers = false

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
            isMyOffers = it.getBoolean(Constants.EXTRA_OFFER_IS_SHOW_MY, false)
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_offer_detail
    }

    override fun initComponents(view: View) {
        ImageLoader("offer.url", ivUserPhoto)
        ivUserPhoto.setOnClickListener {
            onViewProfileDetail()
        }
        tvPerformerName.setOnClickListener {
            onViewProfileDetail()
        }
        tvPerformerName.text = offer.user.getName()
        tvTaskPrice.text = "${offer.price} Rub"
        tvPaymentType.text = getString(R.string.payment_type_cash)
        tvOfferDescription.text = offer.comment
        initButtons()
        initPresenter(view)
    }

    private fun initButtons() {
        if (isMyOffers) {
            btSetMyLocal.visibility = View.GONE
            btSendMessage.visibility = View.GONE
        } else {
            btSetMyLocal.visibility = View.VISIBLE
            btSendMessage.visibility = View.VISIBLE
        }
        if (taskId == offer.task.id) {
            btSetMyLocal.text = "Отменить исполнение"
        }
        btSetMyLocal.setOnClickListener {
            if (taskId != offer.task.id) {
                presenter.setOfferForUser(taskId, offer.id)
            }
        }
        btSendMessage.setOnClickListener {
            onSendMessage()
        }
    }

    private fun initPresenter(view: View) {
        presenter = TaskOfferPresenter(this, view.context)
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

    private fun onViewProfileDetail() {
        val args = Bundle()
        args.putParcelable(Constants.EXTRA_USER, offer.user)
        navigateTo(R.id.profileUserFragment, args)
    }

    override fun onSaveOfferResult(offer: Offer) {
        //
    }

    override fun onOffersResult(offerList: List<Offer>) {
        //
    }

    override fun onSetOfferForUser(offer: Offer) {
        onSetMyLocal()
    }

    override fun onMyOffersResult(offers: List<Offer>) {
        //
    }

    override fun onTaskStatusResult(offer: Offer) {
        //
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
