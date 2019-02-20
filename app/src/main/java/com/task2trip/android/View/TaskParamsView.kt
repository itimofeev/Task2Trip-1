package com.task2trip.android.View

import com.task2trip.android.Model.Task

interface TaskParamsView: BaseView {
    fun onSaveTaskResult(task: Task)
}