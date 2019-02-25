package com.task2trip.android.UI.Fragment.Search

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_search_filter.*

class SearchFilterFragment : BaseFragment() {
    private var userRole = ""

    override fun getArgs(args: Bundle?) {
        args?.let {
            userRole = it.getString(Constants.EXTRA_USER_ROLE, "")
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_search_filter
    }

    override fun initComponents(view: View) {
        btSearch.setOnClickListener {
            onSearchClick()
        }
    }

    private fun onSearchClick() {
        val args = Bundle()
        with(args) {
            putString(Constants.EXTRA_TASK_SEARCH_CATEGORY, etCategory.text.toString())
            putString(Constants.EXTRA_TASK_SEARCH_COUNTRY_CITY, etCountryAndCity.text.toString())
            putString(Constants.EXTRA_TASK_SEARCH_STATUS, etStatus.text.toString())
            putString(Constants.EXTRA_USER_ROLE, userRole)
        }
        navigateTo(R.id.searchFragment, args)
    }
}
