package com.jordan.booksexchange.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
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

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var bigItemAdapter: GroupAdapter<GroupieViewHolder>
    private lateinit var smallItemAdapter: GroupAdapter<GroupieViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create home view model
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        // Get the user from the database
        homeViewModel.currentUser.observe(viewLifecycleOwner, { user ->
            // set the user name to the view
            val text = "Hello, ${user.name}"
            user_name_id_text.animate().alpha(1f).duration = 800
            user_name_id_text.text = text
        })

        // Get all books from the database
        HandelBigItemRv()

        // Move to notification fragment when the user click on the icon
        MoveToNotificationFragment()
    }

    private fun HandelBigItemRv() {
        bigItemAdapter = GroupAdapter()
        big_item_rv.adapter = bigItemAdapter
        big_item_rv.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
        homeViewModel.books.observe(viewLifecycleOwner, {
            bigItemAdapter.clear()
            if (it != null) {
                for (book in it)
                    bigItemAdapter.add(BigItem(book)
                        { postId, bookName ->
                            openDetailsFragment(postId, bookName)
                        }
                    )
            }
        })
    }

//    private fun HandelSmallItemRv(){
//
//
//        smallItemAdapter = GroupAdapter()
//
//        for (bookTopic in BookTopic.values())
//            smallItemAdapter.add(SmallItem(bookTopic))
//
//        small_item_rv.adapter = smallItemAdapter
//        small_item_rv.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL
//            ,false)
//    }

    fun openDetailsFragment(postId: String, bookName: String) {
        val navController = findNavController()
        navController.navigate(
            HomeFragmentDirections.actionHomeFragmentToPostDetailsFragment(
                postId,
                bookName
            )
        )
    }

    fun MoveToNotificationFragment() {

        noti_image_view.setOnClickListener() {
            val navController = findNavController()
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToNotificationFragment())
        }
    }
}