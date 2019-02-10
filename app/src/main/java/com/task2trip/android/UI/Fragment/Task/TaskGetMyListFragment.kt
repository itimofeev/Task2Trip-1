package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.TabFragmentTitle
import com.task2trip.android.Model.TaskAddCategory
import com.task2trip.android.Model.User.User
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.Model.User.UserRole
import com.task2trip.android.R
import com.task2trip.android.UI.Activity.MainActivity
import com.task2trip.android.UI.Adapter.TabAdapter
import com.task2trip.android.UI.Adapter.TaskAddCategoryAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import kotlinx.android.synthetic.main.fragment_task_get_my_list.*

class TaskGetMyListFragment : BaseFragment(), ItemClickListener<TaskAddCategory> {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_get_my_list
    }

    override fun initComponents(view: View) {
        var user: User = UserImpl()
        if (activity is MainActivity) {
            user = (activity as MainActivity).getUser()
        }
        when(user.getRole()) {
            UserRole.NOT_AUTHORIZED -> {
                emptyFrame.visibility = View.VISIBLE
                framePerformer.visibility = View.GONE
                rvTaskList.visibility = View.GONE

                initCategoryRecycleView(view)
                btCreateTask.setOnClickListener {
                    createTaskOnClick()
                }
            }
            UserRole.TRAVELER -> {
                emptyFrame.visibility = View.GONE
                framePerformer.visibility = View.GONE
                rvTaskList.visibility = View.VISIBLE

                initTravelerTaskList(view)
            }
            UserRole.PERFORMER -> {
                emptyFrame.visibility = View.GONE
                framePerformer.visibility = View.VISIBLE
                rvTaskList.visibility = View.GONE

                initViewPager()
            }
        }
    }

    private fun createTaskOnClick() {
        navigateTo(R.id.taskCategoryFragment, Bundle())
    }

    private fun initViewPager() {
        fragmentManager?.let {
            val adapter = TabAdapter(it)
            adapter.addItem(TabFragmentTitle(TaskListFragment(), "Я путешественник"))
            adapter.addItem(TabFragmentTitle(TaskListFragment(), "Я исполнитель"))
            vpTaskList.adapter = adapter
            vpTaskList.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    //
                }
            })
            tabTaskList.setupWithViewPager(vpTaskList)
        }
    }

    private fun initTravelerTaskList(view: View) {
        //
    }

    private fun initCategoryRecycleView(view: View) {
        val adapter = TaskAddCategoryAdapter(MockData.dataTaskAddCategory())
        adapter.setClickListener(this)
        rvTaskCategory.setHasFixedSize(true)
        rvTaskCategory.layoutManager = LinearLayoutManager(view.context)
        rvTaskCategory.adapter = adapter
    }

    override fun onItemClick(item: TaskAddCategory, position: Int) {
        val args = Bundle()
        args.putParcelableArrayList(Constants.EXTRA_TASK_CATEGORY_LIST, ArrayList(MockData.dataTaskAddCategory()))
        args.putInt(Constants.EXTRA_TASK_CATEGORY_SELECTED_POSITION, position)
        navigateTo(R.id.taskAddParamsFragment, args)
    }
}