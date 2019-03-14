package com.task2trip.android.Model.Task

import com.task2trip.android.Model.GeoCountryCity

data class TaskSaveModel(val name: String,
                         val description: String,
                         val categoryId: String,
                         val budgetEstimate: Int,
                         val fromDate: String,
                         val toDate: String,
                         val place: GeoCountryCity)