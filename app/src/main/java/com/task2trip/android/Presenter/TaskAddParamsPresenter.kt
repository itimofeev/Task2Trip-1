package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Task.Task
import com.task2trip.android.Model.Task.TaskSaveModel
import com.task2trip.android.View.TaskParamsView
import retrofit2.Call

class TaskAddParamsPresenter(val view: TaskParamsView, context: Context) : BasePresenter(view, context) {

    fun saveTask(taskSaveModel: TaskSaveModel) {
        view.onProgress(true)
        val req: Call<Task> = getApi().saveTask(taskSaveModel)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onSaveTaskResult(response.body() ?: MockData.getEmptyTask())
                } else {
                    view.onMessage("Запрос прошел, но есть ошибка ${response.code()}")
                }
            }
        }
    }

    fun editTask(taskId: String, taskSaveModel: TaskSaveModel) {
        view.onProgress(true)
        val req: Call<Task> = getApi().updateTask(taskId, taskSaveModel)
        req.enqueue {
            onResponse = { response ->
                view.onProgress(false)
                if (response.code() in 200..299) {
                    view.onUpdateTaskResult(response.body() ?: MockData.getEmptyTask())
                } else {
                    view.onMessage("Запрос прошел, но есть ошибка ${response.code()}")
                }
            }
        }
    }
}