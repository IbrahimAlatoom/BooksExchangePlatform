package com.jordan.booksexchange.items

import com.jordan.booksexchange.R
import com.jordan.booksexchange.models.BookTopic
import com.jordan.booksexchange.models.User
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.topic_item.view.*

class TopicItem(val topics :String): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.card_item_topic_name_text
            .text= topics.toString()
    }



    override fun getLayout(): Int {
        return R.layout.topic_item
    }


}