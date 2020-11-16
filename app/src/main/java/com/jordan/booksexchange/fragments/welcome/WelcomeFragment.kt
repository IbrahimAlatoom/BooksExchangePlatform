package com.jordan.booksexchange.fragments.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.R

class WelcomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // Firebase.auth.signOut()
        checkUser()

    }
    private fun checkUser(){
        val navController = findNavController()
        val auth = Firebase.auth
        val user = auth.currentUser
        if (user == null) {
            navController.navigate(WelcomeFragmentDirections.actionWelcomeFragmentToStartFragment())
        }
        else
            navController.navigate(WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment())
    }
}