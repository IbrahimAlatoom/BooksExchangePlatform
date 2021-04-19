package com.jordan.booksexchange.fragments.notification

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(childFragmentManager: FragmentManager) : FragmentPagerAdapter(childFragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragmentList = ArrayList<Fragment>()
    private val fragmenTitletList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmenTitletList[position]
    }
    fun addFragment(fragment:Fragment,title:String){
        fragmenTitletList.add(title)
        fragmentList.add(fragment)
    }
}