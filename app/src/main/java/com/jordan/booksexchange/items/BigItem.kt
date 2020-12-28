package com.jordan.booksexchange.items

import android.view.View
import androidx.core.view.isVisible
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.R
import com.jordan.booksexchange.models.Book
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.big_item.view.*

class BigItem(private val book: Book , val openDetailsFragment :(String, String) -> Unit): Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.big_item_uni_name.text = book.university.toString()
        viewHolder.itemView.big_item_sch_name.text = book.topic.toString()
        viewHolder.itemView.big_item_description_and_name.text = book.description
        viewHolder.itemView.big_item_book_name.text= book.name

        viewHolder.itemView.setOnClickListener(){
            openDetailsFragment.invoke(book.bookId, book.name)
        }

        viewHolder.itemView.big_item_request_button.setOnClickListener {
            openDetailsFragment.invoke(book.bookId, book.name)
        }
    }

    override fun getLayout(): Int {
        return R.layout.big_item
    }
}