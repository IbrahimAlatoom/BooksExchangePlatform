package com.jordan.booksexchange.fragments.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jordan.booksexchange.R
import kotlinx.android.synthetic.main.fragment_tabbed_notification.*


class TabbedNotificationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabbed_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpTabs()

    }
    private fun setUpTabs(){
        val adapter = PagerAdapter(childFragmentManager)
        adapter.addFragment(InboxFragment(),"Inbox")
        adapter.addFragment(RequestsFragment(),"Requests")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)


    }
}