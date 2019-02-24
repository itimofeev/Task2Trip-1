package com.task2trip.android.View

import com.task2trip.android.Model.Offer

interface TaskOfferView: BaseView {
    fun onSaveOfferResult(offer: Offer)
}