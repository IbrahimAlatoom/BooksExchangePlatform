package com.jordan.booksexchange.fragments.chatting

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.models.Chat
import com.jordan.booksexchange.models.ChatUser

class ChatViewModel : ViewModel() {

    private val _chats = MutableLiveData<List<ChatUser>>()
    val chatUsers: LiveData<List<ChatUser>>
        get() = _chats

    companion object {
        fun sendChat(chat: Chat, sender: String, target: String) {
            FirebaseFirestore.getInstance().collection("chats/$sender/chats/$target/messages")
                .add(chat).addOnSuccessListener {
                    Firebase.firestore.collection("chats/$sender/users")
                        .document(target).set(ChatUser(target))
                }
        }
    }

    // Get all users the has a chat with the current logged in user
    fun getAllUsers() {
        val currentId = Firebase.auth.currentUser?.uid.toString()
        val db = Firebase.firestore
        db.collection("chats/$currentId/users")
            .get().addOnSuccessListener {
                _chats.value = it.toObjects<ChatUser>()
            }
    }
}