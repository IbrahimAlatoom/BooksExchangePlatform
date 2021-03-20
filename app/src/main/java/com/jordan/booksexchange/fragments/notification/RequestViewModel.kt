package com.jordan.booksexchange.fragments.notification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.models.Request
import com.jordan.booksexchange.models.User

class RequestViewModel : ViewModel() {
    var myRequests = MutableLiveData<List<Request>>()
    var inboxRequests = MutableLiveData<List<Request>>()

    init {
        requestsDividor()
    }

    fun requestsDividor() {
        val userId = Firebase.auth.uid
        val db = Firebase.firestore

        // Get real time updates instead of one shot
        db.collection("users").document(userId!!).addSnapshotListener { snapshot, e ->
            if (snapshot != null && snapshot.exists()) {
                val user = snapshot.toObject(User::class.java)

                val myRequests: MutableList<Request> = mutableListOf()
                val inboxRequests: MutableList<Request> = mutableListOf()

                for (i: Request in user?.requests!!) {
                    if (userId != i.userId) {
                        inboxRequests.add(i)
                    } else {
                        myRequests.add(i)
                    }
                }
                this.myRequests.value = myRequests
                this.inboxRequests.value = inboxRequests
            }
        }
    }

    fun removeRequest(request: Request) {
        val db = Firebase.firestore
        val userId = Firebase.auth.currentUser?.uid
        db.collection("users").document(userId!!)
            .get().addOnSuccessListener {
                val user = it.toObject(User::class.java)
                if (user != null) {
                    user.requests.remove(request)
                    db.collection("users").document(userId).update(
                        "requests",
                        user.requests
                    ).addOnSuccessListener {
                        db.collection("users").document(request.publisherId!!)
                            .get().addOnSuccessListener {
                                val otherUser = it.toObject(User::class.java)
                                if (otherUser != null) {
                                    otherUser.requests.remove(request)
                                    db.collection("users").document(request.publisherId).update(
                                        "requests",
                                        otherUser.requests
                                    )
                                }
                            }
                    }
                }
            }
    }
}
