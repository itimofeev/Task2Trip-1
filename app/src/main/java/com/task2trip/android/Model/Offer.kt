package com.task2trip.android.Model

import com.task2trip.android.Model.User.UserImpl

data class Offer(val id: String,
                 val comment: String,
                 val price: Int,
                 val user: UserImpl,
                 val createTime: String,
                 val chosenTime: String)