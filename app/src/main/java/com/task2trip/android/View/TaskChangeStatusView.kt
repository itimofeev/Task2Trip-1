package com.task2trip.android.View

import com.task2trip.android.Model.Offer

interface TaskChangeStatusView: BaseView {
    fun onTaskStatusResult(offer: Offer)
}