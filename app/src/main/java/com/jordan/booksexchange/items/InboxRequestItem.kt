package com.jordan.booksexchange.items

import com.jordan.booksexchange.R
import com.jordan.booksexchange.fragments.chatting.ChatViewModel
import com.jordan.booksexchange.fragments.chatting.ChatViewModel.Companion.sendChat
import com.jordan.booksexchange.models.Chat
import com.jordan.booksexchange.models.Request
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.inbox_request_item.view.*


class InboxRequestItem(val request :Request, val deleteAction: (Request)->Unit) :Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.request_item_book_name.text = request.bookName
        viewHolder.itemView.request_item_reject.setOnClickListener {
            deleteAction(request)
        }

        viewHolder.itemView.request_item_accept.setOnClickListener {
            deleteAction(request)
            sendChat(Chat("Hello, Request Accepted", System.currentTimeMillis(),
                request.publisherId!!, request.userId!!), request.publisherId, request.userId, request.userId)

            sendChat(Chat("Hello, Request Accepted", System.currentTimeMillis(),
                request.publisherId, request.userId), request.userId, request.publisherId, request.publisherId)
        }
    }

    override fun getLayout(): Int {
        return R.layout.inbox_request_item
    }
}
