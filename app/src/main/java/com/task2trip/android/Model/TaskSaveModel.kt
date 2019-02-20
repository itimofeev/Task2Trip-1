package com.task2trip.android.Model

data class TaskSaveModel(val name: String,
                         val description: String,
                         val categoryId: String,
                         val budgetEstimate: Int,
                         val fromDate: String,
                         val toDate: String)