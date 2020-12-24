package com.jordan.booksexchange.fragments.notification

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.models.Request
import com.jordan.booksexchange.models.User

class RequestViewModel :ViewModel() {
    var myRequests = MutableLiveData<List<Request>>()
    var inboxRequests = MutableLiveData<List<Request>>()
    init{
        requestsDividor()
    }




    fun requestsDividor(){
        var userId = Firebase.auth.uid
        var db = Firebase.firestore
        db.collection("users").document(userId!!).get().addOnSuccessListener {
            var user =it.toObject(User ::class.java)

            var myRequests : MutableList<Request> = mutableListOf()
            var inboxRequests : MutableList<Request> = mutableListOf()

            for(i :Request in user?.requests!!)
            {   Log.i("inssss",i.toString())
                if(userId != i.userId)
                {
                    inboxRequests.add(i)
                }
                else{
                    myRequests.add(i)
                }
            }
            this.myRequests.value = myRequests
            this.inboxRequests.value = inboxRequests
            Log.i("inssss",myRequests.toString())
        }
    }



}
