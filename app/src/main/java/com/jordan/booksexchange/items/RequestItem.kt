package com.jordan.booksexchange.items

import com.jordan.booksexchange.R
import com.jordan.booksexchange.models.Book
import com.jordan.booksexchange.models.Request
import com.jordan.booksexchange.models.User
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.request_item.view.*


class RequestItem(val request :Request, val deleteAction: (Request)->Unit) :Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.request_item_book_name.text = request.bookName
        viewHolder.itemView.request_item_reject.setOnClickListener {
            deleteAction(request)
        }
    }

    override fun getLayout(): Int {
        return R.layout.request_item
    }
}
