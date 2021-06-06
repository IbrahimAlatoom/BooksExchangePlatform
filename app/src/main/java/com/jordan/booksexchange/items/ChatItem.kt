package com.jordan.booksexchange.items

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.R
import com.jordan.booksexchange.models.ChatUser
import com.jordan.booksexchange.models.User
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.chat_item.view.*

interface OnChatItemClickListener{
    fun onClick(userId:String)
}
class ChatItem (val chatUser: ChatUser, private val onChatItemClickListener: OnChatItemClickListener) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        var user = User()
        val db = Firebase.firestore
        db.collection("users").document(chatUser.name).get().addOnSuccessListener {
            user = it.toObject(User::class.java)!!
            viewHolder.itemView.chat_item_user_name.text = user.name
        }
            viewHolder.itemView.setOnClickListener{
                onChatItemClickListener.onClick(chatUser.userId)
            }
        }
    override fun getLayout(): Int {
        return R.layout.chat_item
    }
}