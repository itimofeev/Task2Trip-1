package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.Presenter.TaskCategoryPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TaskCategoryAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.TaskCategoryView
import kotlinx.android.synthetic.main.fragment_task_add_category.*
import java.util.*

class TaskAddCategoryFragment : BaseFragment(), TaskCategoryView, ItemClickListener<TaskCategory> {
    private lateinit var presenter: TaskCategoryPresenter

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_add_category
    }

    override fun initComponents(view: View) {
        toolbar.title = "11234"
        //toolbar.setNavigationIcon(R.drawable.vector_ic_arrow_left)
        toolbar.setOnMenuItemClickListener {
            item: MenuItem? -> true
        }
        toolbar.setNavigationOnClickListener {
            //What to do on back clicked
        }
        setToolbar(toolbar)
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
        rvTaskCategory?.adapter = adapter
    }

    override fun onProgress(isProgress: Boolean) {
        if (isProgress) {
            viewLoadAndMessage?.show()
        } else {
            viewLoadAndMessage?.hide()
        }
        viewLoadAndMessage?.setProgress(isProgress)
    }
}
