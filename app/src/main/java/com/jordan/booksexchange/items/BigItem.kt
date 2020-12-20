package com.jordan.booksexchange.items

import com.jordan.booksexchange.R
import com.jordan.booksexchange.models.Book
import com.jordan.booksexchange.models.BookTopic
import com.jordan.booksexchange.models.getTopicBookName
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.big_item.view.*

class BigItem(private val book: Book , val openDetailsFragment :(String) -> Unit): Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.big_item_text.text = book.name
        viewHolder.itemView.setOnClickListener(){
            openDetailsFragment.invoke(book.bookId)
        }
    }

    override fun getLayout(): Int {
        return R.layout.big_item
    }
}