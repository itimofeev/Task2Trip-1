package com.task2trip.android.UI.Fragment.Profile

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Model.UserCategory
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.ProfileCategoryAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import kotlinx.android.synthetic.main.fragment_profile_category.*

class ProfileCategoryFragment : BaseFragment(), ItemClickListener<UserCategory> {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_profile_category
    }

    override fun initComponents(view: View) {
        val adapter = ProfileCategoryAdapter(getItems())
        adapter.setClickListener(this)
        rvCategoryList.setHasFixedSize(true)
        rvCategoryList.layoutManager = LinearLayoutManager(view.context)
        rvCategoryList.adapter = adapter
    }

    private fun getItems(): List<UserCategory> {
        val items = ArrayList<UserCategory>()

        items.add(UserCategory("Маршруты путешествий", false))
        items.add(UserCategory("Гиды и проводники", false))
        items.add(UserCategory("Трансфер", false))
        items.add(UserCategory("Помощники в путешествиях", false))

        return items
    }

    override fun onItemClick(item: UserCategory, position: Int) {
        //
    }
}