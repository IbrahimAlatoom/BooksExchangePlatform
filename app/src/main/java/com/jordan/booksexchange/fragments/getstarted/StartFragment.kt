package com.jordan.booksexchange.fragments.getstarted

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jordan.booksexchange.R
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spannableString = SpannableString(sign_up_text.text.toString())
        spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0);
        sign_up_text.text = spannableString

        // Handel navigate to sign up fragment
        navController = findNavController()
        navigateToSignUp()
        navigateToSignIn()
    }

    private fun navigateToSignUp(){
        sign_up_text.setOnClickListener(){
           navController.navigate(StartFragmentDirections.actionStartFragmentToSignUpFragment())
        }
    }
    private fun navigateToSignIn(){
        move_to_sign_in.setOnClickListener(){
            navController.navigate(StartFragmentDirections.actionStartFragmentToSignInFragment())
        }
    }
}
