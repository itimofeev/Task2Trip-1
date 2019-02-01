package com.task2trip.android.UI.Activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import androidx.annotation.NonNull

class MainActivity : AppCompatActivity(), MainActivityView {
    private lateinit var presenter: MainActivityPresenter
    private lateinit var navController: NavController
    private lateinit var localStoreManager: LocalStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolBarVisibility(false)
        initComponents()
    }

    private fun initComponents() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
        bottomNavigation.setOnNavigationItemSelectedListener {
                item -> navigateApp(navController, item.itemId, Bundle())
        }

        presenter = MainActivityPresenter(this)
        localStoreManager = LocalStoreManager(this)
        showBottomPanel()
        presenter.setNavigation(if (getToken().isNotEmpty()) R.id.taskInfoFragment else R.id.loginRegisterFragment)
    }

    private fun showBottomPanel() {
        bottomNavigation?.visibility = if (getToken().isNotEmpty()) View.VISIBLE else View.GONE
    }

    private fun getToken(): String {
        return localStoreManager.get(Constants.EXTRA_USER_TOKEN, "").toString()
    }

    override fun navigateTo(@IdRes resourceId: Int, args: Bundle?) {
        setToolBarVisibility(true)
        showBottomPanel()
        navigateApp(navController, resourceId, args)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuId = presenter.getLastMenu()
        return if (menuId == 0) {
            false
        } else {
            menuInflater.inflate(menuId, menu)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return navigateApp(navController, item.itemId, Bundle()) || super.onOptionsItemSelected(item)
    }

    private fun navigateApp(navController: NavController, @IdRes resourceId: Int, args: Bundle?): Boolean {
        setDefaultMenu()
        setToolBarVisibility(true)
//        findNavController()
//            .navigate(R.id.action_splashFragment_to_homeFragment,
//                null,
//                NavOptions.Builder()
//                    .setPopUpTo(R.id.splashFragment,
//                        true).build()
//            )
        when(resourceId) {
            R.id.loginRegisterFragment -> {
                navController.popBackStack()
                navController.navigate(resourceId)
                setToolBarVisibility(false)
                setToolBarTitle("Вход / зарегистрация")
                //window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
            R.id.loginFragment -> {
                navController.navigate(resourceId)
                setToolBarTitle("Войти")
            }
            R.id.taskInfoFragment -> {
                navController.popBackStack()
                navController.navigate(resourceId)
                setToolBarTitle("Task2Trip")
            }
            R.id.taskAddParamsFragment, R.id.taskCategoryFragment, R.id.taskListFragment -> {
                navController.navigate(resourceId)
                setToolBarTitle("Добавить задание")
            }
            R.id.registrationFragment -> {
                navController.navigate(resourceId)
                setToolBarTitle("Регистрация")
            }
            R.id.messageFragment -> {
                navController.navigate(resourceId)
                setToolBarVisibility(false)
                setToolBarTitle("Уведомления")
            }
            R.id.searchFragment -> {
                navController.navigate(resourceId)
                setToolBarTitle("Результат: список заданий")
            }
            R.id.searchFilterFragment -> {
                navController.navigate(resourceId)
                presenter.setLastMenu(R.menu.menu_serch_filter)
                setToolBarTitle("Поиск заданий")
            }
            R.id.profileFragment, R.id.profileCategoryFragment, R.id.profileMainInfoFragment,
            R.id.profileContactsFragment, R.id.profileAboutFragment -> {
                presenter.setLastMenu(R.menu.menu_settings)
                navController.navigate(resourceId)
                setToolBarTitle("Профиль")
            }
            R.id.settingsFragment -> {
                navController.navigate(resourceId)
                setToolBarTitle("Настройки")
            }
            R.id.settingsBlackListFragment -> {
                navController.navigate(resourceId)
                presenter.setLastMenu(R.menu.menu_serch_filter)
                setToolBarTitle("Черный список")
            }
            R.id.settingsNotificationFragment -> {
                navController.navigate(resourceId)
                setToolBarTitle("Уведомления")
            }
            else -> {
                navController.navigate(R.id.loginRegisterFragment)
                setToolBarTitle("Task2Trip")
            }
        }
        invalidateOptionsMenu()
        return true
    }

    private fun setToolBarVisibility(isVisible: Boolean) {
        if (isVisible) {
            supportActionBar?.show()
        } else {
            supportActionBar?.hide()
        }
    }

    private fun setToolBarTitle(@NonNull title: String) {
        supportActionBar?.title = title
    }

    private fun setDefaultMenu() {
        presenter.setLastMenu(0)
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