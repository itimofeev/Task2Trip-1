package com.task2trip.android.UI.Fragment.Search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_search.*

class SearchFragment : BaseFragment() {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_search
    }

    override fun initComponents(view: View) {
        rvSearchResultList.layoutManager = LinearLayoutManager(view.context)
    }
}
