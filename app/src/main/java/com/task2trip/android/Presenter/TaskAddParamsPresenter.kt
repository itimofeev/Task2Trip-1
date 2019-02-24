package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Task.Task
import com.task2trip.android.Model.Task.TaskSaveModel
import com.task2trip.android.View.TaskParamsView
import retrofit2.Call

class TaskAddParamsPresenter(val view: TaskParamsView, context: Context) : BasePresenter(context) {

    fun saveTask(taskSaveModel: TaskSaveModel) {
        view.onProgress(true)
        val req: Call<Task> = getApi().saveTask(taskSaveModel)
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    view.onSaveTaskResult(response.body() ?: MockData.getEmptyTask())
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