package com.jordan.booksexchange.fragments.chatting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.R
import com.jordan.booksexchange.items.ChatItem
import com.jordan.booksexchange.items.OnChatItemClickListener
import com.jordan.booksexchange.models.Chat
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment(), OnChatItemClickListener {
    private lateinit var chatItemAdapter: GroupAdapter<GroupieViewHolder>
    private lateinit var chatViewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatItemAdapter = GroupAdapter()
        chat_rv.adapter = chatItemAdapter

        chat_rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,
        false)

        // Get the chat View Model
        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        chatViewModel.getAllUsers()

        chatViewModel.chatUsers.observe(viewLifecycleOwner){
            chatItemAdapter.clear()
            for(user in it){
                chatItemAdapter.add(ChatItem(user,this))
            }

            (chat_rv.layoutManager as LinearLayoutManager).scrollToPosition(chatItemAdapter.itemCount - 1)
        }


    }
    override fun onClick(userId:String) {
        findNavController().navigate(ChatFragmentDirections.actionChatFragmentToMessagesFragment(userId))
    }
}