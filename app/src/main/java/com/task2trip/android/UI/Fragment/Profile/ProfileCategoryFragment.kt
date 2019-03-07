package com.task2trip.android.UI.Fragment.Profile

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.Presenter.TaskCategoryPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TaskCategorySearchAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.TaskCategoryView
import kotlinx.android.synthetic.main.fragment_profile_category.*

class ProfileCategoryFragment : BaseFragment(), TaskCategoryView, ItemClickListener<TaskCategory> {
    private lateinit var presenter: TaskCategoryPresenter

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_profile_category
    }

    override fun initComponents(view: View) {
        initPresenter(view)
        btNextStep.setOnClickListener {
            navigateTo(R.id.profileContactsFragment, Bundle())
        }
        initRecycleView(view)
    }

    private fun initPresenter(view: View) {
        presenter = TaskCategoryPresenter(this, view.context)
        presenter.getCategoryList()
    }

    private fun initRecycleView(view: View) {
        rvCategoryList.setHasFixedSize(true)
        rvCategoryList.layoutManager = LinearLayoutManager(view.context)
    }

    override fun onCategoryList(categoryList: List<TaskCategory>) {
        val adapter = TaskCategorySearchAdapter(categoryList)
        adapter.setClickListener(this)
        rvCategoryList.adapter = adapter
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }

    override fun onItemClick(item: TaskCategory, position: Int) {
        //
    }
}