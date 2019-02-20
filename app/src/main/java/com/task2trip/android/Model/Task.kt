package com.task2trip.android.Model

data class Task(val id: String,
                val name: String,
                val description: String,
                val createTime: String,
                val fromDate: String,
                val toDate: String,
                val budgetEstimate: Int,
                val status: String,
                val newDate: String,
                val inProgressTime: String,
                val finishedTime: String,
                val canceledTime: String,
                val category: TaskCategory)