package com.example.familyapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.familyapp.ui.family.AllFamiliesFragment
import com.example.familyapp.ui.family.MyFamilyFragment

private const val NUM_TABS = 2

class FamilyTabAdapter(fragmentManager: FragmentManager, lifeCycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifeCycle) {
    override fun getItemCount(): Int {
        return  NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyFamilyFragment()
            else -> {
                AllFamiliesFragment()
            }
        }
    }
}