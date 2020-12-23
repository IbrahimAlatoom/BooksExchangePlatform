package com.jordan.booksexchange.fragments.postdetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.R
import com.jordan.booksexchange.fragments.home.HomeViewModel
import com.jordan.booksexchange.models.Book
import com.jordan.booksexchange.models.Request
import kotlinx.android.synthetic.main.fragment_post_details.*
import java.util.EnumSet.of


class PostDetailsFragment : Fragment() {
    private lateinit var postDetailsViewModel : PostDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postDetailsViewModel = ViewModelProviders.of(this).get(PostDetailsViewModel::class.java)
        var postId = PostDetailsFragmentArgs.fromBundle(requireArguments()).postId
        Log.i("PostD", postId)
        postDetailsViewModel.setPostId(postId)
        postDetailsViewModel.postDetails.observe(viewLifecycleOwner) {

            if (it != null) {
                Log.i("postD", it.name.toString())
                post_details_bname.text = it.name
                post_details_bdetails.text = it.description
                post_details_bttopic.text = it.topic.toString()
                post_details_buni.text = it.university.toString()

                post_details_request_button.isClickable = true

            }
        }
        requestButton()
    }
    fun requestButton()
    {
        post_details_request_button.setOnClickListener(){
            val user =  Firebase.auth.uid
            val otherUser = postDetailsViewModel.postDetails.value?.publisherId
            val request = Request(user,otherUser
                ,postDetailsViewModel.getPostId())
            postDetailsViewModel.requestHandler(otherUser!!,request)
            postDetailsViewModel.requestHandler(user!!,request)

        }
    }
    }



