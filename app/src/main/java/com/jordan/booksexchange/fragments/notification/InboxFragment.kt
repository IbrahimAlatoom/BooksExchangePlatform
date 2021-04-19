package com.jordan.booksexchange.fragments.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jordan.booksexchange.R
import com.jordan.booksexchange.items.InboxRequestItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_inbox.*
import kotlinx.android.synthetic.main.fragment_request.*

class InboxFragment : Fragment() {
    private lateinit var requestViewModel: RequestViewModel
    private lateinit var inboxRequestItemAdapter: GroupAdapter<GroupieViewHolder>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inbox, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestViewModel = ViewModelProviders.of(this).get(RequestViewModel::class.java)
        inboxRequestItemAdapter = GroupAdapter()



        inboxRv.adapter = inboxRequestItemAdapter
        inboxRv.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
        // Get inbox requests
        requestViewModel.inboxRequests.observe(viewLifecycleOwner) {
            if (it != null) {
                inboxRequestItemAdapter.clear()
                for (request in it) {
                    inboxRequestItemAdapter.add(
                        InboxRequestItem(
                            request,
                            deleteAction = requestViewModel::removeRequest
                        )
                    )
                }
            }
        }
    }
}

