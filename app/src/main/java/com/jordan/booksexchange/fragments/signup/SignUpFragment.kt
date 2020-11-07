package com.jordan.booksexchange.fragments.signup

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
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        handelSignUp()
    }

    // Function to handel sign up with null information
    private fun handelSignUp() {
        sign_up_button.setOnClickListener() {
            val name = first_name_plain_text.text.toString()
            val email = email_plain_text.text.toString()
            val password = password_plain_text.text.toString()
            val phone = phone_plain_text.text.toString()
            // check if all rights
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                Toast.makeText(requireContext(), "please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            sign_up_button.isClickable = false
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {

                        // Sign in success, update UI with the signed-in user's information
                        Log.d("sign up", "createUserWithEmail:success")

                        // If sign up happens successfully move to choose topics fragment
                        val user = auth.currentUser
                        navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToTopicFragment())
                        }
                    else {
                        // If sign in fails, display a message to the user.
                        Log.w("sign up", "createUserWithEmail:failure", task.exception)
                    }
                }

        }
    }


}

