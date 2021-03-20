package com.jordan.booksexchange.items

import com.jordan.booksexchange.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

interface OnChatItemClickListener{
    fun onClick(userId:String)
}
class ChatItem (val userId:String, private val onChatItemClickListener: OnChatItemClickListener) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            viewHolder.itemView.setOnClickListener{
                onChatItemClickListener.onClick(userId)
            }
        }
    override fun getLayout(): Int {
        return R.layout.chat_item
    }
}