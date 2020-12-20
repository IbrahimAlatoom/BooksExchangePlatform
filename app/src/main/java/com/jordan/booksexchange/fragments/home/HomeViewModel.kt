package com.jordan.booksexchange.fragments.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.models.Book
import com.jordan.booksexchange.models.User

class HomeViewModel : ViewModel() {
    val currentUser = MutableLiveData<User>()
    val books = MutableLiveData<List<Book>>()
    init {
        getCurrentUser()
        getAllBooks()
    }
    private fun getCurrentUser(){
        val userId = Firebase.auth.uid
        Firebase.firestore.collection("users")
            .document(userId.toString()).get().addOnSuccessListener {
                currentUser.value = it.toObject(User::class.java)
            }
    }
    private fun getAllBooks(){
        val db = Firebase.firestore
        db.collection("Posts").get().addOnSuccessListener {
            books.value = it.toObjects(Book ::class.java)
        }


    }

}