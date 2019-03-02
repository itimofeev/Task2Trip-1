package com.task2trip.android.UI.Dialog

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.task2trip.android.Common.Constants
import android.view.LayoutInflater
import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TaskCategorySearchAdapter
import com.task2trip.android.UI.Listener.ItemClickListener

class SearchCategoryDialog: DialogFragment(), ItemClickListener<TaskCategory> {
    var categoryList: ArrayList<TaskCategory> = ArrayList()

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
                categoryList.addAll(lst)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return if (activity != null) {
            val view: View = getResizeView(activity!!)
            initRecycleView(view)
            return AlertDialog.Builder(activity!!)
                .setPositiveButton("ok") {
                        dialog, _ -> dialog.dismiss()
                }
                .setNegativeButton("Отмена") {
                        dialog, _ -> dialog.dismiss()
                }
                .setView(view)
                .create()
        } else {
            super.onCreateDialog(savedInstanceState)
        }
    }

    private fun getResizeView(activity: Activity): View {
        val displayRectangle = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(displayRectangle)
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.dialog_search_category, null)
        layout.minimumWidth = (displayRectangle.width() * 0.5f).toInt()
        layout.minimumHeight = (displayRectangle.height() * 0.5f).toInt()
        return layout
    }

    private fun initRecycleView(view: View) {
        val rvSearchCategory: RecyclerView = view.findViewById<RecyclerView>(R.id.rvSearchCategory)
        val adapter = TaskCategorySearchAdapter(categoryList)
        adapter.setClickListener(this)
        rvSearchCategory.layoutManager = LinearLayoutManager(view.context)
        rvSearchCategory.adapter = adapter
    }

    override fun onItemClick(item: TaskCategory, position: Int) {
        //
    }
}