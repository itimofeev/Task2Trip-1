package com.task2trip.android.View

import com.task2trip.android.Model.Task.TaskCategory

interface TaskCategoryView: BaseView {
    fun onCategoryList(categoryList: List<TaskCategory>)
}