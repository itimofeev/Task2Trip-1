package com.task2trip.android.UI.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.task2trip.android.Model.TabFragmentTitle

class TabAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val items: MutableList<TabFragmentTitle> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return items[position].fragment
    }

    fun addItem(fragmentAndTitle: TabFragmentTitle) {
        items.add(fragmentAndTitle)
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return items[position].title
    }
}
