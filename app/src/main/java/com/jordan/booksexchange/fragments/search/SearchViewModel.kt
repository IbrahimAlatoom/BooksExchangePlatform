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

class SearchViewModel(university :String , school :String) : ViewModel() {
    val post = MutableLiveData<Book>()
    init {
        getPosts(university,school)
    }

    private fun getPosts(university :String , school :String) {
        val db =
            Firebase.firestore.collection("Posts").whereEqualTo(university, University.values())
                .whereEqualTo(school, BookTopic.values()).get().addOnSuccessListener {

                }



    }
}
