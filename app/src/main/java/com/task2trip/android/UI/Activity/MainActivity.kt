package com.task2trip.android.UI.Activity

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.User.User
import com.task2trip.android.Model.User.UserRole
import com.task2trip.android.Presenter.MainActivityPresenter
import com.task2trip.android.R
import com.task2trip.android.View.MainActivityView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityView {
    private lateinit var presenter: MainActivityPresenter
    private lateinit var navController: NavController
    private var user: User = MockData.getEmptyUser()
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
        presenter.setNavigation(if (user.isAuthorized()) R.id.taskListPerformerFragment else R.id.loginRegisterFragment)
    }

    private fun initToolBar() {
        setSupportActionBar(toolBarApp)
        this.actionBar = supportActionBar
        setToolBarVisibility(false)
    }

    private fun initNavigation() {
        navController = Navigation.findNavController(this, navHostID)
        navController.addOnDestinationChangedListener { navController: NavController, navDestination: NavDestination, bundle: Bundle? ->
            if (navDestination.id == R.id.loginRegisterFragment) {
                setToolBarParams(false, getString(R.string.login_into_app), false)
            }
        }
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

    private fun navigateApp(navController: NavController, @IdRes resourceIdNav: Int, args: Bundle?): Boolean {
        setDefaultMenu()
        setToolBarVisibility(true)
        val resourceId = navigationHook(resourceIdNav)
        when(resourceId) {
            R.id.loginRegisterFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(false, "Вход / Регистрация", false)
            }
            R.id.loginFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.login_into_app), false)
            }
            R.id.registrationFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.registration), false)
            }
            R.id.taskCategoryFragment -> {
                navController.clearBackStack(resourceId, args)
                setToolBarParams(true, "Добавить задание", false)
            }
            R.id.taskAddParamsFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, "Параметры задания", true)
            }
            R.id.messageFragment -> {
                navController.clearBackStack(resourceId, args)
                setToolBarParams(true, "Входящие", false)
            }
            R.id.searchFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, "Результат: список заданий", true)
                presenter.setLastMenu(R.menu.menu_serch_filter)
            }
            R.id.searchFilterFragment -> {
                val params = args ?: Bundle()
                params.putString(Constants.EXTRA_USER_ROLE, user.getRole().name)
                navController.clearBackStack(resourceId, params)
                setToolBarParams(true, "Поиск заданий", false)
            }
            R.id.profileFragment -> {
                navController.clearBackStack(resourceId, args)
                setToolBarParams(true, "Профиль", false)
                presenter.setLastMenu(R.menu.menu_settings)
            }
            R.id.profileCategoryFragment, R.id.profileMainInfoFragment,
            R.id.profileContactsFragment, R.id.profileAboutFragment -> {
                navController.navigate(resourceId, args)
                setToolBarTitle("Профиль. Параметры")
            }
            R.id.profileUserFragment -> {
                navController.navigate(resourceId, args)
                setToolBarTitle("Просмотр профиля")
            }
            R.id.settingsFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, "Настройки", true)
            }
            R.id.settingsBlackListFragment -> {
                navController.navigate(resourceId, args)
                presenter.setLastMenu(R.menu.menu_serch_filter)
                setToolBarTitle("Черный список")
            }
            R.id.settingsNotificationFragment -> {
                navController.navigate(resourceId, args)
                setToolBarTitle("Уведомления")
            }
            R.id.taskListPerformerFragment, R.id.taskListPerformerPagerFragment,
            R.id.taskListNotAuthorizedFragment, R.id.taskListTravelerFragment -> {
                val params = args ?: Bundle()
                params.putString(Constants.EXTRA_USER_ID, this.user.getId())
                params.putString(Constants.EXTRA_USER_ROLE, this.user.getRole().name)
                navController.clearBackStack(resourceId, params)
                setToolBarParams(true, "Мои задания", false)
            }
            R.id.taskAddOfferFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, "Добавить предложение", true)
            }
            R.id.taskDetailOfferPagerFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, "Задание №YYY", true)
            }
            R.id.taskOffersFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, "Предложения", true)
            }
            R.id.offerDetailFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, "Просмотр предложения", true)
            }
            R.id.taskDetailsFragment -> {
                navController.navigate(resourceId, args)
                setToolBarTitle("Задача №ххх")
            }
            android.R.id.home -> {
                navController.popBackStack()
            }
            else -> {
                navController.navigate(R.id.loginRegisterFragment)
                setToolBarTitle("Task2Trip")
            }
        }
        invalidateOptionsMenu()
        return true
    }

    private fun navigationHook(@IdRes resourceId: Int): Int {
        val userRole = getUser().getRole()
        return when(resourceId) {
            R.id.taskListPerformerFragment -> {
                return when (userRole) {
                    UserRole.NOT_AUTHORIZED -> {
                        R.id.taskListNotAuthorizedFragment
                    }
                    UserRole.LOCAL -> {
                        R.id.taskListPerformerPagerFragment
                    }
                    UserRole.TRAVELER -> {
                        R.id.taskListTravelerFragment
                    }
                }
            }
            R.id.taskCategoryFragment -> {
                return when (userRole) {
                    UserRole.NOT_AUTHORIZED -> {
                        R.id.loginRegisterFragment
                    }
                    UserRole.LOCAL, UserRole.TRAVELER -> {
                        R.id.taskCategoryFragment
                    }
                }
            }
            R.id.profileFragment -> {
                return when (userRole) {
                    UserRole.NOT_AUTHORIZED -> {
                        R.id.loginRegisterFragment
                    }
                    UserRole.LOCAL, UserRole.TRAVELER -> {
                        R.id.profileFragment
                    }
                }
            }
            R.id.messageFragment -> {
                return when (userRole) {
                    UserRole.NOT_AUTHORIZED -> {
                        R.id.loginRegisterFragment
                    }
                    UserRole.LOCAL, UserRole.TRAVELER -> {
                        R.id.messageFragment
                    }
                }
            }
            else -> {
                resourceId
            }
        }
    }

    private fun NavController.clearBackStack(nextNavigation: Int?, args: Bundle?) {
        var isNotEmptyStack: Boolean = true
        while (isNotEmptyStack) {
            isNotEmptyStack = this.popBackStack()
        }
        nextNavigation?.let {
            this.navigate(it, args)
        }
    }

    private fun setToolBarParams(isVisible: Boolean, title: String, isHasBackButton: Boolean) {
        if (!isVisible) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            }
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
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

    override fun logoutUser() {
        setUser(MockData.getEmptyUser())
        this.user.saveUserData(applicationContext)
        navigateTo(R.id.taskListPerformerFragment, Bundle())
    }

    private fun getUser(): User {
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