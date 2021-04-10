package com.jordan.booksexchange.fragments.signin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.R
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        handelSignIn()

    }

    private fun handelSignIn() {
        sign_in_button.setOnClickListener() {
            val email = sign_in_email_plain_text.text.toString()
            val password = sign_in_password_plain_text.text.toString()

            // Check if user is signed in (non-null) and update UI accordingly.
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        navController.navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment())
                    } else {
                        Toast.makeText(
                            requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}
