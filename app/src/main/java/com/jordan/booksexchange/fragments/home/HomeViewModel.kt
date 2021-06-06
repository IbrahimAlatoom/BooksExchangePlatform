package com.jordan.booksexchange.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.models.Book
import com.jordan.booksexchange.models.User

class HomeViewModel() : ViewModel() {
    val currentUser = MutableLiveData<User>()
    val books = MutableLiveData<List<Book>>()
    var userName = MutableLiveData<String>()

    init {
        if(Firebase.auth.currentUser != null) {
            getCurrentUser()
            getAllBooks()
        }
    }

    private fun getCurrentUser(){
        val userId = Firebase.auth.uid
        Firebase.firestore.collection("users")
            .document(userId.toString()).get().addOnSuccessListener {
                currentUser.value = it.toObject(User::class.java)
            }
    }

    private fun getAllBooks() {
        val db = Firebase.firestore
        db.collection("Posts").get().addOnSuccessListener {
            books.value = it.toObjects(Book::class.java)
        }
    }

    fun getUserFromId(publisherId:String) {
        var user = User()
        val db = Firebase.firestore
        db.collection("users").document(publisherId).get().addOnSuccessListener {
            user = it.toObject(User::class.java)!!
            this.userName.value = user.name
        }
    }
}

