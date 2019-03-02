package com.task2trip.android.UI.Dialog

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.task2trip.android.Common.Constants
import android.view.LayoutInflater
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TaskCategorySearchAdapter
import com.task2trip.android.UI.Listener.ItemClickListener

class SearchCategoryDialog: DialogFragment(), ItemClickListener<TaskCategory> {
    private var categoryList: ArrayList<TaskCategory> = ArrayList()

    companion object {
        fun getInstance(categoryList: ArrayList<TaskCategory>): SearchCategoryDialog {
            val dialog = SearchCategoryDialog()
            val args = Bundle()
            args.putParcelableArrayList(Constants.EXTRA_TASK_CATEGORY_LIST, categoryList)
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val lst: ArrayList<TaskCategory>? = it.getParcelableArrayList(Constants.EXTRA_TASK_CATEGORY_LIST)
            if (!lst.isNullOrEmpty()) {
                categoryList.clear()
                for (item in lst) {
                    categoryList.add(item.copy())
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return if (activity != null) {
            val displayRectangle = Rect()
            activity?.window?.decorView?.getWindowVisibleDisplayFrame(displayRectangle)
            val view: View = getResizeView(activity!!, getMinimumSize(displayRectangle))
            initRecycleView(view)

            return AlertDialog.Builder(activity!!)
                .setPositiveButton("ok") { dialog, _ ->
                        setSelectedResult()
                        dialog.dismiss()
                }
                .setNegativeButton("Отмена") { dialog, _ ->
                    targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_CANCELED, Intent())
                    dialog.dismiss() }
                .setView(view)
                .create()
        } else {
            super.onCreateDialog(savedInstanceState)
        }
    }

    private fun setSelectedResult() {
        val intent = Intent()
        intent.putParcelableArrayListExtra(Constants.EXTRA_TASK_CATEGORY_LIST, categoryList.clone() as ArrayList<TaskCategory>)
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
    }

    private fun getResizeView(activity: Activity, size: Point): View {
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.dialog_search_category, null)
        layout.minimumWidth = size.x
        layout.minimumHeight = size.y
        return layout
    }

    private fun getMinimumSize(displayRectangle: Rect): Point {
        val minimumWidth = (displayRectangle.width() * 0.5f).toInt()
        val minimumHeight = (displayRectangle.height() * 0.5f).toInt()
        return Point(minimumWidth, minimumHeight)
    }

    private fun initRecycleView(view: View) {
        val rvSearchCategory: RecyclerView = view.findViewById<RecyclerView>(R.id.rvSearchCategory)
        val adapter = TaskCategorySearchAdapter(categoryList)
        adapter.setClickListener(this)
        rvSearchCategory.layoutManager = LinearLayoutManager(view.context)
        rvSearchCategory.adapter = adapter
    }

    override fun onItemClick(item: TaskCategory, position: Int) {
        item.isSelected = !categoryList[position].isSelected
        categoryList[position] = item
    }
}