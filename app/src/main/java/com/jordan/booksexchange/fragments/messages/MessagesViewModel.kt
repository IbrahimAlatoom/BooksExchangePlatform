package com.jordan.booksexchange.fragments.messages

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.jordan.booksexchange.models.Chat

class MessagesViewModel: ViewModel() {
    private val _messages = MutableLiveData<List<Chat>>()
    val messages: LiveData<List<Chat>>
        get() = _messages


    fun getChatMessages(userId: String, otherId: String) {
        FirebaseFirestore.getInstance().collection("chats/$userId/chats/$otherId/messages")
            .orderBy("timestamp").addSnapshotListener {
                snapshot, e ->
                if (snapshot != null) {
                    _messages.value = snapshot.toObjects<Chat>()
                }
            }
    }
}