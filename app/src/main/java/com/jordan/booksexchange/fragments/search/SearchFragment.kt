package com.jordan.booksexchange.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jordan.booksexchange.R
import com.jordan.booksexchange.items.PostItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {
    private lateinit var postItemAdapter : GroupAdapter<GroupieViewHolder>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        HanedelSearchRv()
    }

    private fun HanedelSearchRv(){
        postItemAdapter = GroupAdapter()
        postItemAdapter.add(PostItem("Math 102"))
        postItemAdapter.add(PostItem("Cs 102"))
        postItemAdapter.add(PostItem("English 102"))
        search_rv.adapter = postItemAdapter
        search_rv.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL
            ,false)
    }



}