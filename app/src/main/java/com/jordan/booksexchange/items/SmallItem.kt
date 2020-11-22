package com.jordan.booksexchange.items

import com.jordan.booksexchange.R
import com.jordan.booksexchange.models.BookTopic
import com.jordan.booksexchange.models.getTopicBookName
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.small_item.view.*

class SmallItem(private val book: BookTopic): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.smal_item_text.text= getTopicBookName(book)
    }

    override fun getLayout(): Int {
        return R.layout.small_item
    }
}