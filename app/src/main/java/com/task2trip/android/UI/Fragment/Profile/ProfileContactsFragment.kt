package com.task2trip.android.UI.Fragment.Profile

import android.os.Bundle
import android.view.View
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile_contacts.*

class ProfileContactsFragment : BaseFragment() {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_profile_contacts
    }

    override fun initComponents(view: View) {
        val country = etCountry.text.toString()
        val city = etCity.text.toString()
        btNext.setOnClickListener {
            //navigateTo(R.id.profileFragment)
        }
    }
}