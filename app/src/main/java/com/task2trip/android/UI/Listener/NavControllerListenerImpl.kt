package com.task2trip.android.UI.Listener

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination

class NavControllerListenerImpl: NavController.OnDestinationChangedListener {
    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        controller.navigate(destination.id)
    }
}