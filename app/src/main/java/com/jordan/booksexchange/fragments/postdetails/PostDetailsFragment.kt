package com.jordan.booksexchange.fragments.postdetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.R
import com.jordan.booksexchange.fragments.home.HomeViewModel
import com.jordan.booksexchange.models.Book
import com.jordan.booksexchange.models.Request
import com.jordan.booksexchange.models.User
import kotlinx.android.synthetic.main.fragment_post_details.*
import java.util.EnumSet.of


class PostDetailsFragment : Fragment() {
    private lateinit var postDetailsViewModel: PostDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postDetailsViewModel = ViewModelProviders.of(this).get(PostDetailsViewModel::class.java)

        // Get post id and book name from the home fragment
        var postId = PostDetailsFragmentArgs.fromBundle(requireArguments()).postId
        val bookName = PostDetailsFragmentArgs.fromBundle(requireArguments()).bookName

        // Set the data to the view model
        postDetailsViewModel.setPostId(postId)
        postDetailsViewModel.bookName = bookName

        // Get the book
        postDetailsViewModel.postDetails.observe(viewLifecycleOwner) {
            if (it != null) {
                Log.i("postD", it.name.toString())
                post_details_bname.text = it.name
                post_details_bdetails.text = it.description
                post_details_bttopic.text = it.topic.toString()
                post_details_buni.text = it.university.toString()

                // Check  if the publisherId is the current user id
                // Hide the request button
                if (it.publisherId == Firebase.auth.currentUser?.uid) {
                    post_details_request_button.visibility = View.GONE
                } else {
                    // Show the request button
                    post_details_request_button.visibility = View.VISIBLE
                    post_details_request_button.translationY = -50f
                    post_details_request_button.animate().translationYBy(50f).alpha(1f)
                        .duration = 700

                    post_details_request_button.isClickable = true
                }
            }
        }

        // Set button click listener
        requestButton()
    }

    fun requestButton() {
        post_details_request_button.setOnClickListener() {
            // Create the request object
            val user = Firebase.auth.uid
            val otherUser = postDetailsViewModel.postDetails.value?.publisherId
            val request = Request(
                user, otherUser, postDetailsViewModel.getPostId()
            )

            // Save the book request into the user and the other user
            postDetailsViewModel.requestHandler(otherUser!!, request)
            postDetailsViewModel.requestHandler(user!!, request)

            // Stop the request button
            post_details_request_button.isClickable = false

            Toast.makeText(
                requireContext(), "Request sent successfully",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}



