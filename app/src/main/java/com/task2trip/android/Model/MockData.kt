package com.task2trip.android.Model

import com.task2trip.android.Model.Task.Task
import com.task2trip.android.Model.Task.TaskAddCategory
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.Model.User.User
import com.task2trip.android.Model.User.UserImpl

class MockData {
    companion object {
        fun dataTaskAddCategory(): List<TaskAddCategory> {
            val items = ArrayList<TaskAddCategory>()
            items.add(TaskAddCategory(1, "", "Category 001", "Category Description 001"))
            items.add(TaskAddCategory(2, "", "Маршруты путешествия", "Category Description 002"))
            items.add(TaskAddCategory(3, "", "Category 003", "Category Description 003"))
            items.add(TaskAddCategory(4, "", "Category 004", "Category Description 004"))
            items.add(TaskAddCategory(5, "", "Category 005", "Category Description 005"))
            items.add(TaskAddCategory(6, "", "Category 006", "Category Description 006"))
            return items
        }

        fun getEmptyTask(): Task {
            return Task("", "", "", "",
                "", "", 0, "",
                "", "", "", "", getEmptyCategory())
        }

        fun getEmptyCategory(): TaskCategory {
            return TaskCategory("", "", "", "")
        }

        fun getEmptyUser(): User {
            return UserImpl()
        }

        fun getEmptyOffer(): Offer {
            return Offer("", "", 0, getEmptyUser() as UserImpl, "", "")
        }
    }
}