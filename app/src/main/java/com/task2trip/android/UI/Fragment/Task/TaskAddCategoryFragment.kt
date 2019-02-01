package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Model.TaskAddCategory
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TaskAddCategoryAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import kotlinx.android.synthetic.main.fragment_task_add_category.*

class TaskAddCategoryFragment : BaseFragment(), ItemClickListener<TaskAddCategory> {
    private var lastSelectedItem: TaskAddCategory? = null

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_add_category
    }

    override fun initComponents(view: View) {
        btNextStep.setOnClickListener {
            onNextClick()
        }
        initRecycleView(view)
    }

    private fun initRecycleView(view: View) {
        val adapter = TaskAddCategoryAdapter(getMockItems())
        adapter.setClickListener(this)
        rvTaskCategory.setHasFixedSize(true)
        rvTaskCategory.layoutManager = LinearLayoutManager(view.context)
        rvTaskCategory.adapter = adapter
    }

    private fun getMockItems(): List<TaskAddCategory> {
        val items = ArrayList<TaskAddCategory>()
        items.add(TaskAddCategory("Category 001"))
        items.add(TaskAddCategory("Category 002"))
        items.add(TaskAddCategory("Category 003"))
        items.add(TaskAddCategory("Category 004"))
        items.add(TaskAddCategory("Category 005"))
        return items
    }

    private fun onNextClick() {
        this.lastSelectedItem?.let {
            navigateTo(R.id.taskAddParamsFragment, Bundle())
            this.lastSelectedItem = null
        } ?: onMessage("Выберите категорию из списка!")
    }

    override fun onItemClick(item: TaskAddCategory, position: Int) {
        this.lastSelectedItem = item
    }
}
