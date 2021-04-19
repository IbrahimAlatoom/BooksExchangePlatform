package com.jordan.booksexchange.fragments.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jordan.booksexchange.R
import com.jordan.booksexchange.items.RequestItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_request.*
import kotlinx.android.synthetic.main.fragment_requests.*


class RequestsFragment : Fragment() {
    private lateinit var requestViewModel: RequestViewModel
    private lateinit var requestItemAdapter: GroupAdapter<GroupieViewHolder>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestViewModel = ViewModelProviders.of(this).get(RequestViewModel::class.java)

        requestItemAdapter = GroupAdapter()

        requestsRv.adapter = requestItemAdapter
        requestsRv.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )

        // Get my requests
        requestViewModel.myRequests.observe(viewLifecycleOwner){
            if (it != null) {
                requestItemAdapter.clear()
                for (request in it) {
                    requestItemAdapter.add(
                        RequestItem(
                            request,
                            deleteAction = requestViewModel::removeRequest
                        )
                    )
                }
            }
        }
    }
}