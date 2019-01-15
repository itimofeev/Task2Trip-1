package com.task2trip.android.UI.Activity

import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.task2trip.android.Presenter.MainActivityPresenter
import com.task2trip.android.R
import com.task2trip.android.View.MainActivityView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityView {
    private lateinit var presenter: MainActivityPresenter
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)

        presenter = MainActivityPresenter(this)
        initComponents()
    }

    private fun initComponents() {
        //presenter.setNavigation(R.id.loginRegisterFragment)
    }

    override fun navigateTo(@IdRes resourceId: Int, args: Bundle?) {
        supportActionBar?.show()
        when(resourceId) {
            R.id.loginRegisterFragment -> {
                navController.navigate(resourceId)
                supportActionBar?.hide()
                window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
            R.id.registrationFragment -> {
                navController.navigate(resourceId)
            }
            R.id.loginFragment -> {
                navController.navigate(resourceId)
            }
            R.id.taskSearchFragment -> {
                navController.navigate(resourceId)
            }
            R.id.taskAddFragment -> {
                navController.navigate(resourceId)
            }
            R.id.messageFragment -> {
                navController.navigate(resourceId)
            }
            R.id.profileFragment -> {
                navController.navigate(resourceId)
            }
            else -> {
                navController.navigate(R.id.loginRegisterFragment)
                supportActionBar?.title = "Заказы"
            }
        }
    }

    override fun onMessage(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT)
            .show()
    }


    override fun onProgress(isProgress: Boolean) {
        //
    }

    private fun setVisibilityBackButton(isVisible: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isVisible)
        supportActionBar?.setDisplayShowHomeEnabled(isVisible)
    }
}
