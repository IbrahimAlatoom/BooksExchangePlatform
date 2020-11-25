package com.jordan.booksexchange.fragments.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.models.Book
import com.jordan.booksexchange.models.BookTopic
import com.jordan.booksexchange.models.University

class SearchViewModel() : ViewModel() {
    val postList = MutableLiveData<List<Book>>()
    init {

    }

     fun getPosts(university :String , topic :String) {
        val db =
            Firebase.firestore.collection("Posts").whereEqualTo("university", university)
                .whereEqualTo("topic", topic).get().addOnSuccessListener {
                       postList.value = it.toObjects(Book::class.java)
                }
    }
}
