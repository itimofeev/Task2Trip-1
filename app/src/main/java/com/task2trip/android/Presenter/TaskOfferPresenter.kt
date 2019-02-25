package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Offer
import com.task2trip.android.Model.OfferForSave
import com.task2trip.android.View.TaskOfferView
import retrofit2.Call

class TaskOfferPresenter(val view: TaskOfferView, context: Context): BasePresenter(context) {
    fun offerSaveByTask(taskId: String, offerForSave: OfferForSave) {
        view.onProgress(true)
        val req: Call<Offer> = getApi().sendOfferByTaskId(taskId, offerForSave)
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    view.onSaveOfferResult(response.body() ?: MockData.getEmptyOffer())
                } else {
                    view.onMessage("Запрос прошел, но есть ошибка ${response.code()}")
                }
                view.onProgress(false)
            }

            onFailure = { onFailure ->
                view.onMessage("Сетевая ошибка ${onFailure?.message}")
                view.onProgress(false)
            }
        }
    }
}