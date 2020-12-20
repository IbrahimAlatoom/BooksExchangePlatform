package com.jordan.booksexchange.fragments.post
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.R
import com.jordan.booksexchange.models.Book
import com.jordan.booksexchange.models.StringToBookTopic
import com.jordan.booksexchange.models.University
import kotlinx.android.synthetic.main.fragment_post.*


class PostFragment : Fragment() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        post_button.setOnClickListener {


            val bookName = post_book_name.text.toString()
            val uniName = post_uni_spinner.selectedItem.toString()
            val schName = post_schoole_spinner.selectedItem.toString()
            val detail = post_details.text.toString()

            if (bookName.isEmpty() || uniName.isEmpty() || schName.isEmpty() || detail.isEmpty()) {
                Toast.makeText(requireContext(), "please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
//            Upload Post to Firebase
            val db = Firebase.firestore
            val userId = Firebase.auth.currentUser?.uid
            val ref: DocumentReference = db.collection("Posts").document()
            val postId = ref.id
            val post: Book = Book(
                bookName, userId, postId ,StringToUni(uniName),
                StringToBookTopic(schName), detail, ""
            )

            ref.set(post).addOnSuccessListener {
                navController.navigate(PostFragmentDirections.actionPostFragmentToHomeFragment())

                Toast.makeText(
                    requireContext(),
                    "You Have Added Post successfully",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }





        }
    }

    private fun StringToUni(uniName: String) : University {

        return when(uniName)
        {
            "Just" -> University.Just
            "JU" -> University.JU
            "Psut" -> University.Psut

            else -> University.Psut
        }


    }
}