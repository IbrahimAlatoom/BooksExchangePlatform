package com.jordan.booksexchange.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.R
import com.jordan.booksexchange.items.BigItem
import com.jordan.booksexchange.items.SmallItem
import com.jordan.booksexchange.models.BookTopic
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel : HomeViewModel
    private lateinit var bigItemAdapter : GroupAdapter<GroupieViewHolder>
    private lateinit var smallItemAdapter : GroupAdapter<GroupieViewHolder>
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

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.currentUser.observe(viewLifecycleOwner,{
            user ->
            user_name_id_text.text = user.name
        })



        HandelBigItemRv()
        HandelSmallItemRv()


    }
    private fun HandelBigItemRv(){
        bigItemAdapter = GroupAdapter()

        for (bookTopic in BookTopic.values())
            bigItemAdapter.add(BigItem(bookTopic))
        big_item_rv.adapter = bigItemAdapter
        big_item_rv.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL
            ,false)
    }
    private fun HandelSmallItemRv(){


        smallItemAdapter = GroupAdapter()

        for (bookTopic in BookTopic.values())
            smallItemAdapter.add(SmallItem(bookTopic))

        small_item_rv.adapter = smallItemAdapter
        small_item_rv.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL
            ,false)
    }
}