package com.jordan.booksexchange.items

import android.graphics.Color
import android.view.View
import com.jordan.booksexchange.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.topic_item.view.*
import org.jetbrains.anko.backgroundColor

class TopicItem(val topics :String , val action :()->Unit): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.card_item_topic_name_text
            .text= topics.toString()
        viewHolder.itemView.topic_item_card.setOnClickListener(::handelHighlightCardColor)

    }

    override fun getLayout(): Int {
        return R.layout.topic_item
    }
    private fun handelHighlightCardColor(view : View){
        view.backgroundColor = Color.GRAY
        action.invoke()
    }


}