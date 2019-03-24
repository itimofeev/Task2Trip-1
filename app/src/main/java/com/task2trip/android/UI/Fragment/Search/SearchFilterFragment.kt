package com.task2trip.android.UI.Fragment.Search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.Location.GeoCountryCity
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.Model.Task.TaskStatus
import com.task2trip.android.Presenter.SearchLocationPresenter
import com.task2trip.android.Presenter.TaskCategoryPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TaskStatusAdapter
import com.task2trip.android.UI.Dialog.SearchCategoryDialog
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Widget.SearchLocationFieldCallback
import com.task2trip.android.View.SearchLocationView
import com.task2trip.android.View.TaskCategoryView
import kotlinx.android.synthetic.main.fragment_search_filter.*

class SearchFilterFragment : BaseFragment(), TaskCategoryView, SearchLocationView, SearchLocationFieldCallback {
    private lateinit var presenterCategory: TaskCategoryPresenter
    private lateinit var presenterSearch: SearchLocationPresenter
    private var userRoleStr = ""
    private val categoryList: ArrayList<TaskCategory> = ArrayList()

    override fun getArgs(args: Bundle?) {
        args?.let {
            userRoleStr = it.getString(Constants.EXTRA_USER_ROLE, "")
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_search_filter
    }

    override fun initComponents(view: View) {
        initPresenter(view)
        initStatuses(view)
        tvCategory.setOnClickListener {
            onCategoryClick()
        }
        etCategory.setOnClickListener {
            onCategoryClick()
        }
        btSearch.setOnClickListener {
            onSearchClick()
        }
        searchLocationView.setSearchLocationFieldCallback(this)
        searchLocationView.setHint(getText(R.string.search_location))
    }

    private fun initPresenter(view: View) {
        presenterCategory = TaskCategoryPresenter(this, view.context)
        presenterCategory.getCategoryList()
        presenterSearch = SearchLocationPresenter(this, view.context)
    }

    private fun initStatuses(view: View) {
        val statusList = ArrayList<TaskStatus>()
        statusList.addAll(TaskStatus.values())
        val adapter = TaskStatusAdapter(view.context,
            R.layout.item_task_status,
            R.layout.item_task_status_drop_down,
            statusList)
        etStatus.adapter = adapter
        etStatus.setSelection(0)
    }

    private fun onCategoryClick() {
        hideKeyboard()
        val dialogBuilder = SearchCategoryDialog.getInstance(this.categoryList)
        dialogBuilder.setTargetFragment(this, Constants.REQUEST_DIALOG_CATEGORY)
        fragmentManager?.let {
            dialogBuilder.show(it, SearchCategoryDialog::class.java.name)
        }
    }

    private fun onSearchClick() {
        hideKeyboard()
        val args = Bundle()
        with(args) {
            val selectedCategoryList: ArrayList<TaskCategory> = ArrayList()
            for (item in categoryList) {
                if (item.isSelected) {
                    selectedCategoryList.add(item)
                }
            }
            putParcelableArrayList(Constants.EXTRA_TASK_CATEGORY_LIST, selectedCategoryList)
            putParcelable(Constants.EXTRA_TASK_SEARCH_LOCATION, searchLocationView.getItem().point)
            putString(Constants.EXTRA_TASK_SEARCH_STATUS, etStatus.selectedItem.toString())
            putString(Constants.EXTRA_USER_ROLE, userRoleStr)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQUEST_DIALOG_CATEGORY) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    val selectedCategory: ArrayList<TaskCategory> = it.getParcelableArrayListExtra(Constants.EXTRA_TASK_CATEGORY_LIST)
                    if (!selectedCategory.isNullOrEmpty()) {
                        this.categoryList.clear()
                        this.categoryList.addAll(selectedCategory)
                        setCategoriesToField()
                    }
                }
            }
        }
    }

    private fun setCategoriesToField() {
        etCategory.setText("")
        for ((index, item) in this.categoryList.withIndex()) {
            val delimiter = ", "
            if (item.isSelected) {
                etCategory.text = etCategory.text?.append(item.defaultValue + delimiter)
            }
            if (index + 1 == this.categoryList.size) {
                val textSizeLength = etCategory.text?.toString()?.length ?: 0
                if (textSizeLength > delimiter.length) {
                    etCategory.setText(etCategory.text.toString().substring(0, textSizeLength - delimiter.length))
                }
            }
        }
    }

    override fun onSearchLocationResult(items: List<GeoCountryCity>) {
        searchLocationView?.setDataForSearch(items)
    }

    override fun onTextLocationChanged(text: String) {
        // Ищем, если хотя бы введено searchLocationView.getMinimumTextSearchSize() символов
        if (text.length >= searchLocationView?.getMinimumTextSearchSize() ?: 0) {
            presenterSearch.getCountryAndCityByText(text)
        }
    }

    override fun onItemLocationChanged(item: GeoCountryCity) {
        //
    }

    override fun onLocationClick() {
        //
    }
}