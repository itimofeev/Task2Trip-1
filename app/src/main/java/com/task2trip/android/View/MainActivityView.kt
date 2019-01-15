package com.task2trip.android.View

import android.os.Bundle

interface MainActivityView: BaseView {
    fun navigateTo(resourceId: Int, args: Bundle?)
}