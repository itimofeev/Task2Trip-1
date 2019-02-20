package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.TaskCategory
import com.task2trip.android.Presenter.TaskCategoryPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TaskCategoryAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.TaskCategoryView
import kotlinx.android.synthetic.main.fragment_task_add_category.*

class TaskAddCategoryFragment : BaseFragment(), TaskCategoryView, ItemClickListener<TaskCategory> {
    private lateinit var presenter: TaskCategoryPresenter

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_add_category
    }

    override fun initComponents(view: View) {
        initRecycleView(view)
        presenter = TaskCategoryPresenter(this, view.context)
        presenter.getCategoryList()
    }

    private fun initRecycleView(view: View) {
        rvTaskCategory.setHasFixedSize(true)
        rvTaskCategory.layoutManager = LinearLayoutManager(view.context)
    }

    override fun onItemClick(item: TaskCategory, position: Int) {
        val args = Bundle()
        args.putParcelableArrayList(Constants.EXTRA_TASK_CATEGORY_LIST, ArrayList((rvTaskCategory.adapter as TaskCategoryAdapter).getItems()))
        args.putInt(Constants.EXTRA_TASK_CATEGORY_SELECTED_POSITION, position)
        navigateTo(R.id.taskAddParamsFragment, args)
    }

    override fun onCategoryList(categoryList: List<TaskCategory>) {
        val adapter = TaskCategoryAdapter(categoryList)
        adapter.setClickListener(this)
        rvTaskCategory.adapter = adapter
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
