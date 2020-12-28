package com.jordan.booksexchange.items

import androidx.recyclerview.widget.RecyclerView
import com.jordan.booksexchange.R
import com.jordan.booksexchange.models.Book
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.post_item.view.*

class PostItem(val book : Book , private val action : (String,String)->Unit) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.post_item_text.text = book.name
        viewHolder.itemView.setOnClickListener(){
            action.invoke(book.bookId,book.name)
        }
    }

    override fun getLayout(): Int {
        return R.layout.post_item
    }
}