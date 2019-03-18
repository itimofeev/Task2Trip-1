package com.task2trip.android.Model.Task

import com.task2trip.android.Model.Location.GeoCountryCity

data class TaskSaveModel(val name: String,
                         val description: String,
                         var categoryId: String,
                         val budgetEstimate: Int,
                         val fromDate: String,
                         val toDate: String,
                         val place: GeoCountryCity)