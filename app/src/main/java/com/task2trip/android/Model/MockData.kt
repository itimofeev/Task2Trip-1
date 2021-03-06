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

        fun getEmptyOfferList(): List<Offer> {
            return ArrayList<Offer>()
        }

        fun getOfferList(): List<Offer> {
            val offers = ArrayList<Offer>()
            offers.add(Offer("1", "test comment 11", 111, getEmptyUser() as UserImpl, "", ""))
            offers.add(Offer("2", "test comment 22", 222, getEmptyUser() as UserImpl, "", ""))
            offers.add(Offer("3", "test comment 33", 333, getEmptyUser() as UserImpl, "", ""))
            offers.add(Offer("4", "test comment 44", 444, getEmptyUser() as UserImpl, "", ""))
            offers.add(Offer("5", "test comment 55", 555, getEmptyUser() as UserImpl, "", ""))
            return offers
        }
    }
}