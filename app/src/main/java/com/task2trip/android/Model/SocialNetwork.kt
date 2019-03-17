package com.task2trip.android.Model

import androidx.annotation.DrawableRes

data class SocialNetwork(val networkName: String,
                         @DrawableRes val networkImage: Int,
                         val userName: String)