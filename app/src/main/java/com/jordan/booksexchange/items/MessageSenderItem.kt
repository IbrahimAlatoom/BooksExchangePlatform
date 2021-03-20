package com.jordan.booksexchange.items

import com.jordan.booksexchange.R
import com.jordan.booksexchange.models.Book
import com.jordan.booksexchange.models.Chat
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.message_item_sender.view.*
import kotlinx.android.synthetic.main.post_item.view.*

class MessageSenderItem(val chat: Chat) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.message_item_sender_text.text = chat.text
    }

    override fun getLayout(): Int {
        return R.layout.message_item_sender
    }
}