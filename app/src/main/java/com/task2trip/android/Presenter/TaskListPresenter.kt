package com.task2trip.android.Presenter

import android.content.Context
import com.task2trip.android.Model.Location.LatLng
import com.task2trip.android.Model.Task.TaskList
import com.task2trip.android.View.TaskListView
import retrofit2.Call

class TaskListPresenter(val view: TaskListView, context: Context) : BasePresenter(context) {

    fun getAllTask() {
        view.onProgress(true)
        val req: Call<TaskList> = getApi().getAllTasks()
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    view.onTaskListResult(response.body() ?: TaskList(emptyList(), 0))
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

    fun getTasksByUserId(userId: String) {
        view.onProgress(true)
        val req: Call<TaskList> = getApi().getTasks(userId)
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    view.onTaskListResult(response.body() ?: TaskList(emptyList(), 0))
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

    fun searchTasks(userId: String? = null,
                    searchString: String? = null,
                    categoryIds: String? = null,
                    skip: Int? = null,
                    limit: Int? = null,
                    status: String? = null,
                    location: LatLng? = null) {
        view.onProgress(true)
        val req: Call<TaskList> = getApi().getTasks(userId, searchString, categoryIds, skip, limit, status,
            location?.lat, location?.long, location?.radius)
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    view.onTaskListResult(response.body() ?: TaskList(emptyList(), 0))
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