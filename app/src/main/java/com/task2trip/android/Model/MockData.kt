package com.task2trip.android.Model

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
    }
}