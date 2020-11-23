package com.jordan.booksexchange.items

import androidx.recyclerview.widget.RecyclerView
import com.jordan.booksexchange.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.post_item.view.*

class PostItem(val bookName : String /*, bookImage:String*/) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.post_item_text.text=bookName
    }

    override fun getLayout(): Int {
        return R.layout.post_item
    }
}