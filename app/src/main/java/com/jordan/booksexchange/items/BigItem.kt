package com.jordan.booksexchange.items

import android.util.Log
import com.bumptech.glide.Glide
import com.jordan.booksexchange.R
import com.jordan.booksexchange.models.Book
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.big_item.view.*

class BigItem(
    private val book: Book,
    val openDetailsFragment: (String, String) -> Unit
) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.big_item_uni_name.text = book.publisherName
        viewHolder.itemView.big_item_sch_name.text = book.university.toString().plus(" -")
        viewHolder.itemView.big_item_description_and_name.text = book.description
        viewHolder.itemView.big_item_book_name.text = book.name
//        viewHolder.itemView.bif_item_book_image.setImageResource()

        viewHolder.itemView.setOnClickListener() {
            openDetailsFragment.invoke(book.bookId, book.name)
        }

        viewHolder.itemView.big_item_request_button.setOnClickListener {
            openDetailsFragment.invoke(book.bookId, book.name)
        }
        Log.i("BigItem", "Url: ${book.imageUrl}")
        Glide
            .with(viewHolder.itemView.context)
            .load(book.imageUrl)
            .into(viewHolder.itemView.bif_item_book_image)

    }

    override fun getLayout(): Int {
        return R.layout.big_item
    }
}