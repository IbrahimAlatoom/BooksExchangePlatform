package com.jordan.booksexchange.fragments.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.R
import com.jordan.booksexchange.fragments.postdetails.PostDetailsViewModel
import com.jordan.booksexchange.items.RequestItem
import com.jordan.booksexchange.models.User
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_request.*

class notificationFragment : Fragment() {
    private lateinit var requestViewModel: RequestViewModel
    private lateinit var requestItemAdapter : GroupAdapter<GroupieViewHolder>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestViewModel = ViewModelProviders.of(this).get(RequestViewModel::class.java)
        requestItemAdapter = GroupAdapter()
        requests_rv.adapter = requestItemAdapter
        requests_rv.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL
            ,false)
        requestViewModel.myRequests.observe(viewLifecycleOwner){
//            requestItemAdapter.clear()
            if (it !=null ){
                for(request in it)
                {
                    requestItemAdapter.add(RequestItem(request))
                }
            }
        }


    }
}
