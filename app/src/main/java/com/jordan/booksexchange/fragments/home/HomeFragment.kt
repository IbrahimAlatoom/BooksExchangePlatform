package com.jordan.booksexchange.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jordan.booksexchange.R
import com.jordan.booksexchange.items.BigItem
import com.jordan.booksexchange.models.BookTopic
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var bigItemAdapter : GroupAdapter<GroupieViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bigItemAdapter = GroupAdapter()
        bigItemAdapter.add(BigItem(BookTopic.SoftwareEngineering))
        big_item_rv.adapter = bigItemAdapter
        big_item_rv.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL
            ,false)


    }
}