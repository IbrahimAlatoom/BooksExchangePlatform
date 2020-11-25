package com.jordan.booksexchange.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jordan.booksexchange.R
import com.jordan.booksexchange.items.PostItem
import com.jordan.booksexchange.models.StringToBookTopic
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {
    private lateinit var postItemAdapter : GroupAdapter<GroupieViewHolder>
    private lateinit var searchViewModel: SearchViewModel
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
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        // init
        HanedelSearchRv()

        search_button.setOnClickListener {
            val university = search_uni_spinner.selectedItem.toString()
            val school = search_school_spinner.selectedItem.toString()
            searchViewModel.getPosts(university, StringToBookTopic(school).name)
        }

        searchViewModel.postList.observe(viewLifecycleOwner, {
            bookList ->
            if(!bookList.isNullOrEmpty()) {
                postItemAdapter.clear()
                for (book in bookList) {
                    postItemAdapter.add(PostItem(book.name))
                }
            }else{
                // Hide the rv
                // show message
                postItemAdapter.clear()
            }
        })


    }

    private fun HanedelSearchRv(){

        postItemAdapter = GroupAdapter()
        search_rv.adapter = postItemAdapter
        search_rv.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL
            ,false)
    }
}