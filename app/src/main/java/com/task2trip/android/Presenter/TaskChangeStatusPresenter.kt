package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Offer
import com.task2trip.android.Model.Task.TaskStatusAndRating
import com.task2trip.android.View.TaskChangeStatusView
import retrofit2.Call

class TaskChangeStatusPresenter(val view: TaskChangeStatusView, context: Context) : BasePresenter(view, context) {

    fun setTaskWithOfferFinished(taskId: String, offerId: String, statusAndRating: TaskStatusAndRating) {
        view.onProgress(true)
        val req: Call<Offer> = getApi().setTaskCompletedOrCanceled(taskId, offerId, statusAndRating)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onTaskStatusResult(response.body() ?: MockData.getEmptyOffer())
                }
            }
        }
    }
}