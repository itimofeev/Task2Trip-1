package com.task2trip.android.UI.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.task2trip.android.Model.TabFragmentTitle

/**
 * For viewPager2
 */
class TabAdapter2(fm: FragmentManager) : FragmentStateAdapter(fm) {
    private val items: MutableList<TabFragmentTitle> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return items[position].fragment
    }

    fun addItem(fragmentAndTitle: TabFragmentTitle) {
        items.add(fragmentAndTitle)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
