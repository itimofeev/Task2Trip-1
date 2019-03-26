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
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.iid.FirebaseInstanceId
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.User.*
import com.task2trip.android.Presenter.MainActivityPresenter
import com.task2trip.android.Presenter.PushPresenter
import com.task2trip.android.R
import com.task2trip.android.View.MainActivityView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityView {
    private lateinit var presenter: MainActivityPresenter
    private lateinit var presenterPush: PushPresenter
    private lateinit var navController: NavController
    private var user: UserImpl = MockData.getEmptyUser()
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

        presenter = MainActivityPresenter(this, applicationContext)
        presenterPush = PushPresenter(this, applicationContext)
        val startScreenId = if (user.isAuthorized()) {
            presenterPush.registerPushToken()
            R.id.taskListPerformerFragment
        } else {
            R.id.loginRegisterFragment
        }
        presenter.setNavigation(startScreenId)
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
        navigateApp(navController, resourceId, args ?: Bundle())
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

    private fun navigateApp(navController: NavController, @IdRes resourceIdNav: Int, args: Bundle): Boolean {
        setDefaultMenu()
        setToolBarVisibility(true)
        val resourceId = navigationHook(resourceIdNav)
        when(resourceId) {
            R.id.loginRegisterFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(false, getString(R.string.title_login_registration), false)
            }
            R.id.loginFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.title_login_into_app), false)
            }
            R.id.registrationFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.title_registration), false)
            }
            R.id.taskCategoryFragment -> {
                navController.clearBackStack(resourceId, args)
                setToolBarParams(true, getString(R.string.title_create_task), false)
            }
            R.id.taskAddParamsFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.title_task_params), true)
            }
            R.id.messageFragment -> {
                args.putString(Constants.EXTRA_USER_ID, user.getId())
                navController.clearBackStack(resourceId, args)
                setToolBarParams(true, getString(R.string.title_inbox), false)
            }
            R.id.messageChatDialogFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, "Диалог с пользователем", true)
            }
            R.id.searchFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.title_result_task_list), true)
                presenter.setLastMenu(R.menu.menu_serch_filter)
            }
            R.id.searchFilterFragment -> {
                args.putString(Constants.EXTRA_USER_ROLE, user.getRole().name)
                navController.clearBackStack(resourceId, args)
                setToolBarParams(true, getString(R.string.title_search_task), false)
            }
            R.id.profileFragment -> {
                args.putParcelable(Constants.EXTRA_USER, user)
                navController.clearBackStack(resourceId, args)
                setToolBarParams(true, getString(R.string.title_profile), false)
                presenter.setLastMenu(R.menu.menu_settings)
            }
            R.id.profileCategoryFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.profile_category), true)
            }
            R.id.profileMainInfoFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.profile_main), true)
            }
            R.id.profileContactsFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.profile_contacts), true)
            }
            R.id.profileAboutFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.profile_about), true)
            }
            R.id.profileUserFragment -> {
                navController.navigate(resourceId, args)
                setToolBarTitle(getString(R.string.title_view_profile))
            }
            R.id.settingsFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.title_settings), true)
            }
            R.id.settingsBlackListFragment -> {
                navController.navigate(resourceId, args)
                presenter.setLastMenu(R.menu.menu_serch_filter)
                setToolBarTitle(getString(R.string.title_black_list))
            }
            R.id.settingsNotificationFragment -> {
                navController.navigate(resourceId, args)
                setToolBarTitle(getString(R.string.title_notifications))
            }
            R.id.taskListPerformerFragment, R.id.taskListPerformerPagerFragment,
            R.id.taskListNotAuthorizedFragment, R.id.taskListTravelerFragment -> {
                args.putString(Constants.EXTRA_USER_ID, this.user.getId())
                args.putString(Constants.EXTRA_USER_ROLE, this.user.getRole().name)
                navController.clearBackStack(resourceId, args)
                setToolBarParams(true, getString(R.string.title_my_tasks), false)
            }
            R.id.taskAddOfferFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.title_add_offer), true)
            }
            R.id.taskDetailOfferPagerFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.title_offer_details_pager), true)
            }
            R.id.taskOffersFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.title_offers), true)
            }
            R.id.offerDetailFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.title_offer_details), true)
            }
            R.id.offersShowMyFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.task_get_my_offers), true)
            }
            R.id.taskDetailsFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, getString(R.string.title_task_details), true)
            }
            R.id.noContentFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, "Нет страницы", true)
            }
            R.id.mapFragment -> {
                navController.navigate(resourceId, args)
                setToolBarParams(true, "Карта", true)
            }
            android.R.id.home -> {
                navController.popBackStack()
            }
            else -> {
                navController.navigate(R.id.noContentFragment)
                setToolBarTitle(getString(R.string.app_name))
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
        if (!isVisible && bottomNavigation?.visibility == View.GONE) {
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
        this.user = user as UserImpl
        this.user.saveUserData(applicationContext)
    }

    override fun setUserProfile(profile: Profile) {
        this.user.setProfile(profile as ProfileImpl)
        this.user.saveUserData(applicationContext)
    }

    override fun setUserRole(role: String) {
        this.user.setRole(UserRole.getName(role))
        this.user.saveUserData(applicationContext)
    }

    override fun onMessage(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
/*
<RatingBar
                android:id="@+id/ratingBar"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                style="?android:attr/ratingBarStyle"/>
defaultRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                smallRatingBar.setRating(rating);
                indicatorRatingBar.setRating(rating);

                Toast.makeText(MainActivity.this, "рейтинг: " + String.valueOf(rating),
                        Toast.LENGTH_LONG).show();
            }
        });

<style name="MyRatingBar" parent="Theme.AppCompat">
    <item name="colorControlNormal">@android:color/holo_green_light</item>
    <item name="colorControlActivated">@android:color/holo_orange_dark</item>
</style>
*/
