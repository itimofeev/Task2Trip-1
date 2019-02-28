package com.task2trip.android.UI.Fragment.Search

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.Presenter.TaskCategoryPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Dialog.SearchCategoryDialog
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.View.TaskCategoryView
import kotlinx.android.synthetic.main.fragment_search_filter.*

class SearchFilterFragment : BaseFragment(), TaskCategoryView {
    private lateinit var presenter: TaskCategoryPresenter
    private var userRole = ""
    private val categoryList: ArrayList<TaskCategory> = ArrayList()

    override fun getArgs(args: Bundle?) {
        args?.let {
            userRole = it.getString(Constants.EXTRA_USER_ROLE, "")
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_search_filter
    }

    override fun initComponents(view: View) {
        initPresenter(view)
        tvCategory.setOnClickListener {
            onCategoryClick()
        }
        etCategory.setOnClickListener {
            onCategoryClick()
        }
        tvCountryAndCity.setOnClickListener {
            onCountryAndCityClick()
        }
        etCountryAndCity.setOnClickListener {
            onCountryAndCityClick()
        }
        btSearch.setOnClickListener {
            onSearchClick()
        }
    }

    private fun initPresenter(view: View) {
        presenter = TaskCategoryPresenter(this, view.context)
        presenter.getCategoryList()
    }

    private fun onCountryAndCityClick() {
        //
    }

    private fun onCategoryClick() {
        val dialogBuilder = SearchCategoryDialog.getInstance(this.categoryList)
        fragmentManager?.let {
            dialogBuilder.show(it, "TAG")
        }
    }

    private fun onSearchClick() {
        val args = Bundle()
        with(args) {
            putString(Constants.EXTRA_TASK_SEARCH_CATEGORY, etCategory.text.toString())
            putString(Constants.EXTRA_TASK_SEARCH_COUNTRY_CITY, etCountryAndCity.text.toString())
            putString(Constants.EXTRA_TASK_SEARCH_STATUS, etStatus.text.toString())
            putString(Constants.EXTRA_USER_ROLE, userRole)
        }
        navigateTo(R.id.searchFragment, args)
    }

    override fun onCategoryList(categoryList: List<TaskCategory>) {
        this.categoryList.clear()
        this.categoryList.addAll(categoryList)
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
