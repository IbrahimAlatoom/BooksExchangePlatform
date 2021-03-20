package com.jordan.booksexchange.fragments.messages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.R
import com.jordan.booksexchange.fragments.chatting.ChatViewModel.Companion.sendChat
import com.jordan.booksexchange.items.MessageReceiverItem
import com.jordan.booksexchange.items.MessageSenderItem
import com.jordan.booksexchange.models.Chat
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_messages.*


class MessagesFragment : Fragment() {

    private lateinit var messagesViewModel: MessagesViewModel
    private lateinit var messagesAdapter: GroupAdapter<GroupieViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messagesViewModel = ViewModelProvider(this).get(MessagesViewModel::class.java)

        val args = MessagesFragmentArgs.fromBundle(requireArguments())

        messagesViewModel.getChatMessages(Firebase.auth.currentUser?.uid!!, args.userId)

        send_chat_button.setOnClickListener {
            val text = chat_edit_text.text.toString()
            val chat = Chat(
                text, System.currentTimeMillis(), Firebase.auth.currentUser?.uid!!, args.userId
            )
            sendChat(
                chat, Firebase.auth.currentUser?.uid!!, args.userId
            )
            sendChat(
                chat, args.userId, Firebase.auth.currentUser?.uid!!
            )

            chat_edit_text.text.clear()
        }

        messagesAdapter = GroupAdapter()
        messages_rv.adapter = messagesAdapter

        messagesViewModel.messages.observe(viewLifecycleOwner) { chats ->
            val currentUserId = Firebase.auth.currentUser?.uid
            messagesAdapter.clear()
            for (chat in chats) {
                if (chat.senderId == currentUserId) {
                    messagesAdapter.add(MessageSenderItem(chat))
                } else {
                    messagesAdapter.add(MessageReceiverItem(chat))
                }
            }
        }

    }
}