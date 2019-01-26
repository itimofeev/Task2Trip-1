package com.task2trip.android.UI.Activity

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.LocalStoreManager
import com.task2trip.android.Presenter.MainActivityPresenter
import com.task2trip.android.R
import com.task2trip.android.View.MainActivityView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityView {
    private lateinit var presenter: MainActivityPresenter
    private lateinit var navController: NavController
    private lateinit var localStoreManager: LocalStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        initComponents()
    }

    private fun initComponents() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)

        presenter = MainActivityPresenter(this)
        localStoreManager = LocalStoreManager(this)
        showBottomPanel()
        presenter.setNavigation(if (getToken().isNotEmpty()) R.id.taskInfoFragment else R.id.profileFragment)
    }

    private fun showBottomPanel() {
        bottomNavigation?.visibility = if (getToken().isNotEmpty()) View.VISIBLE else View.GONE
    }

    private fun getToken(): String {
        return localStoreManager.get(Constants.EXTRA_USER_TOKEN, "").toString()
    }

    override fun navigateTo(@IdRes resourceId: Int, args: Bundle?) {
        supportActionBar?.show()
        when(resourceId) {
            R.id.loginRegisterFragment -> {
                navController.navigate(resourceId)
                supportActionBar?.hide()
                //window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
            R.id.registrationFragment, R.id.loginFragment, R.id.taskSearchFragment, R.id.taskAddFragment,
            R.id.messageFragment, R.id.taskInfoFragment -> {
                navController.navigate(resourceId)
            }
            R.id.profileFragment, R.id.profileCategoryFragment, R.id.profileMainInfoFragment,
            R.id.profileContactsFragment, R.id.profileAboutFragment -> {
                navController.navigate(resourceId)
            }
            R.id.settingsFragment, R.id.settingsBlackListFragment, R.id.settingsNotificationFragment -> {
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
