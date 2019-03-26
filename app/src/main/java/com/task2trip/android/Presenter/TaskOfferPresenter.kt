package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Offer
import com.task2trip.android.Model.OfferForSave
import com.task2trip.android.Model.Task.TaskStatusAndRating
import com.task2trip.android.View.TaskOfferView
import retrofit2.Call

class TaskOfferPresenter(val view: TaskOfferView, context: Context): BasePresenter(view, context) {

    fun offerSaveByTask(taskId: String, offerForSave: OfferForSave) {
        view.onProgress(true)
        val req: Call<Offer> = getApi().sendOfferByTaskId(taskId, offerForSave)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onSaveOfferResult(response.body() ?: MockData.getEmptyOffer())
                }
            }
        }
    }

    fun getOffersByTaskId(taskId: String) {
        view.onProgress(true)
        val req: Call<List<Offer>> = getApi().getOffersByTaskId(taskId)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onOffersResult(response.body() ?: MockData.getEmptyOfferList())//MockData.getOfferList()
                }
            }
        }
    }

    fun setOfferForUser(taskId: String, offerId: String) {
        view.onProgress(true)
        val req: Call<Offer> = getApi().setPerformerOfferForTask(taskId, offerId)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onSetOfferForUser(response.body() ?: MockData.getEmptyOffer())
                }
            }
        }
    }

    fun getMyOffers() {
        view.onProgress(true)
        val req: Call<List<Offer>> = getApi().getMyOffers()
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onMyOffersResult(response.body() ?: MockData.getEmptyOfferList())
                }
            }
        }
    }
}