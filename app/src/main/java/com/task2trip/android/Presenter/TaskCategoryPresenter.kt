package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.TaskCategory
import com.task2trip.android.View.TaskCategoryView
import retrofit2.Call

class TaskCategoryPresenter(val view: TaskCategoryView, context: Context) : BasePresenter(context) {

    fun getCategoryList() {
        view.onProgress(true)
        val req: Call<List<TaskCategory>> = getApi().getCategoryList()
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    view.onCategoryList(response.body() ?: emptyList<TaskCategory>())
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