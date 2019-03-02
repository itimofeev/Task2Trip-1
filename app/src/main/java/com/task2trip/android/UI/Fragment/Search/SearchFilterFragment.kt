package com.task2trip.android.UI.Fragment.Search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.Model.Task.TaskStatus
import com.task2trip.android.Presenter.TaskCategoryPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TaskStatusAdapter
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
        initStatuses(view)
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

    private fun initStatuses(view: View) {
        val statusList = ArrayList<TaskStatus>()
        statusList.addAll(TaskStatus.values())
        val adapter = TaskStatusAdapter(view.context, R.layout.item_task_status, statusList)
        etStatus.adapter = adapter
        etStatus.setSelection(0)
    }

    private fun onCountryAndCityClick() {
        //
    }

    private fun onCategoryClick() {
        hideKeyboard()
        val dialogBuilder = SearchCategoryDialog.getInstance(this.categoryList)
        dialogBuilder.setTargetFragment(this, Constants.REQUEST_DIALOG_CALEGORY)
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
                selectedCategoryList.add(item)
            }
            putParcelableArrayList(Constants.EXTRA_TASK_CATEGORY_LIST, selectedCategoryList)
            putString(Constants.EXTRA_TASK_SEARCH_COUNTRY_CITY, etCountryAndCity.text.toString())
            putString(Constants.EXTRA_TASK_SEARCH_STATUS, etStatus.selectedItem.toString())
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQUEST_DIALOG_CALEGORY) {
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
}
