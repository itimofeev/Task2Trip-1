package com.task2trip.android.View

import com.task2trip.android.Model.TaskList

interface TaskListView: BaseView {
    fun onTaskListResult(taskResult: TaskList)
}