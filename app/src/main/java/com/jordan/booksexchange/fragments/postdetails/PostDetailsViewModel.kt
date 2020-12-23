package com.jordan.booksexchange.fragments.postdetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.models.Book
import com.jordan.booksexchange.models.Request
import com.jordan.booksexchange.models.User

class PostDetailsViewModel()  : ViewModel() {
    var postDetails = MutableLiveData<Book>()
    var checker = false

    init {


    }
    private var postId = ""

    fun setPostId(Id :String){
        postId = Id
        getPost()
    }
    fun getPostId():String{
        return postId
    }
    fun getPost(){
        var post = Firebase.firestore.collection("Posts").document(postId)
            .get().addOnSuccessListener {
                postDetails.value = it.toObject(Book ::class.java)

            }
    }

    fun requestHandler(userId :String,request :Request){
        val db = Firebase.firestore
        val oldRequests = db.collection("users").document(userId!!)
            .get().addOnSuccessListener {
                val user = it.toObject(User ::class.java)
                if (user != null) {
                    user.requests.add(request)
                    db.collection("users").document(userId!!).update("requests",
                        user.requests)
                }
            }
        }

    }
