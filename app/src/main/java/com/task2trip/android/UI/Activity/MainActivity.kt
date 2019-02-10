package com.task2trip.android.UI.Activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import com.task2trip.android.Model.User.User
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.Presenter.MainActivityPresenter
import com.task2trip.android.R
import com.task2trip.android.View.MainActivityView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityView {
    private lateinit var presenter: MainActivityPresenter
    private lateinit var navController: NavController
    private var user: User = UserImpl()
    private var actionBar: ActionBar? = null
    private val navHostID = R.id.nav_host_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
    }

    private fun initComponents() {
        user.initStorageData(applicationContext)
        initToolBar()
        initNavigation()

        presenter = MainActivityPresenter(this)
        presenter.setNavigation(if (user.isAuthorized()) R.id.taskGetMyListFragment else R.id.loginRegisterFragment)
    }

    private fun initToolBar() {
        setSupportActionBar(toolBarApp)
        this.actionBar = supportActionBar
        setToolBarVisibility(false)
    }

    private fun initNavigation() {
        navController = Navigation.findNavController(this, navHostID)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
        bottomNavigation.setOnNavigationItemSelectedListener {
                item -> navigateApp(navController, item.itemId, Bundle())
        }
    }

    private fun showBottomPanel(@IdRes resourceId: Int) {
        bottomNavigation?.visibility = when (resourceId) {
            R.id.loginRegisterFragment, R.id.loginFragment, R.id.registrationFragment -> {
                View.GONE
            }
            else -> {
                View.VISIBLE
            }
        }
    }

    override fun navigateTo(@IdRes resourceId: Int, args: Bundle?) {
        setToolBarVisibility(true)
        showBottomPanel(resourceId)
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
        when(resourceId) {
            R.id.loginRegisterFragment -> {
                navController.navigate(resourceId)
                setToolBarParams(false, "Вход / Регистрация", false)
                //window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
            R.id.loginFragment -> {
                navController.navigate(resourceId)
                setToolBarParams(false, "Вход в приложение", false)
            }
            R.id.registrationFragment -> {
                navController.navigate(resourceId)
                setToolBarParams(false, "Регистрация", false)
            }
            R.id.taskGetMyListFragment -> {
                navController.clearBackStack(resourceId)
                setToolBarParams(true, "Мои задания", false)
            }
            R.id.taskCategoryFragment -> {
                navController.clearBackStack(resourceId)
                setToolBarParams(true, "Добавить задание", false)
            }
            R.id.taskAddParamsFragment, R.id.taskListFragment -> {
                navController.navigate(resourceId)
                setToolBarParams(true, "Параметры задания", true)
            }
            R.id.messageFragment -> {
                navController.clearBackStack(resourceId)
                setToolBarParams(true, "Входящие", false)
            }
            R.id.searchFragment -> {
                navController.navigate(resourceId)
                setToolBarTitle("Результат: список заданий")
            }
            R.id.searchFilterFragment -> {
                navController.clearBackStack(resourceId)
                setToolBarParams(true, "Поиск заданий", false)
                presenter.setLastMenu(R.menu.menu_serch_filter)
            }
            R.id.profileFragment -> {
                navController.clearBackStack(resourceId)
                setToolBarParams(true, "Профиль", false)
            }
            R.id.profileCategoryFragment, R.id.profileMainInfoFragment,
            R.id.profileContactsFragment, R.id.profileAboutFragment -> {
                presenter.setLastMenu(R.menu.menu_settings)
                navController.navigate(resourceId)
                setToolBarTitle("Профиль. Параметры")
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

    private fun NavController.clearBackStack(nextNavigation: Int?) {
        var isNotEmptyStack: Boolean = true
        while (isNotEmptyStack) {
            isNotEmptyStack = this.popBackStack()
        }
        nextNavigation?.let {
            this.navigate(it)
        }
    }

    private fun setToolBarParams(isVisible: Boolean, title: String, isHasBackButton: Boolean) {
        setToolBarVisibility(isVisible)
        setToolBarVisibilityBackButton(isHasBackButton)
        setToolBarTitle(title)
    }

    private fun setToolBarVisibility(isVisible: Boolean) {
        this.actionBar?.let {
            if (isVisible) {
                it.show()
            } else {
                it.hide()
            }
        }
    }

    private fun setToolBarTitle(title: String) {
        this.actionBar?.title = title
    }

    private fun setToolBarVisibilityBackButton(isVisible: Boolean) {
        this.actionBar?.let {
            it.setDisplayHomeAsUpEnabled(isVisible)
            it.setDisplayShowHomeEnabled(isVisible)
        }
    }

    private fun setDefaultMenu() {
        presenter.setLastMenu(0)
    }

    fun getUser(): User {
        return this.user
    }

    override fun setUser(user: User) {
        this.user = user
    }

    override fun onMessage(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}