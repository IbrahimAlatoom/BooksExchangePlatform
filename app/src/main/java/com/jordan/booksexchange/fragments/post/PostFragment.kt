package com.jordan.booksexchange.fragments.post

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.jordan.booksexchange.R
import com.jordan.booksexchange.models.Book
import com.jordan.booksexchange.models.StringToBookTopic
import com.jordan.booksexchange.models.University
import kotlinx.android.synthetic.main.chat_item.*
import kotlinx.android.synthetic.main.fragment_post.*
import java.io.ByteArrayOutputStream


class PostFragment : Fragment() {
    companion object {
        const val PICK_IMAGE_CODE = 1
        const val READ_STORAGE_CODE = 2
    }

    private lateinit var navController: NavController
    private var imageUri: String? = null

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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_CODE && resultCode == Activity.RESULT_OK) {
            // Get image URI
            imageUri = data?.data!!.toString()
            val inputStream = requireActivity().contentResolver.openInputStream(
                data.data!!
            )
            val drawable = Drawable.createFromStream(inputStream, imageUri)
            post_upload_image.setImageDrawable(drawable)
        }
    }

    private fun getImage() {

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_STORAGE_CODE
            )
        } else {
            openGallery()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == READ_STORAGE_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openGallery()
        }
    }

    private fun openGallery() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, PICK_IMAGE_CODE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        post_upload_image.setOnClickListener {
            getImage()
        }

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

            if (imageUri.isNullOrEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Please upload the book image!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

//            Upload Post to Firebase
            progressBar2.visibility = View.VISIBLE
            val db = Firebase.firestore
            val userId = Firebase.auth.currentUser?.uid
            val ref: DocumentReference = db.collection("Posts").document()
            val postId = ref.id


            // Upload the image
            post_upload_image.isDrawingCacheEnabled = true
            post_upload_image.buildDrawingCache()
            val bitmap = post_upload_image.drawable.toBitmap()
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            val storageRef =
                Firebase.storage.reference.child("images/${System.currentTimeMillis()}.jpeg")

            var uploadTask = storageRef.putBytes(data)
            uploadTask.continueWithTask {
                if (!it.isSuccessful) {
                    progressBar2.visibility = View.INVISIBLE
                }
                storageRef.downloadUrl
            }.addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    val post: Book = Book(
                        bookName, userId, postId, StringToUni(uniName),
                        StringToBookTopic(schName), detail, downloadUri.toString()
                    )
                    ref.set(post).addOnSuccessListener {
                        progressBar2.visibility = View.INVISIBLE
                        navController.navigate(PostFragmentDirections.actionPostFragmentToHomeFragment())
                        Toast.makeText(
                            requireContext(),
                            "You Have Added Post successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                        .addOnFailureListener{
                            Toast.makeText(
                                requireContext(),
                                "Submit Fail",
                                Toast.LENGTH_SHORT
                            ).show()
                            progressBar2.visibility = View.INVISIBLE
                        }
                }
            }
        }
    }

    private fun StringToUni(uniName: String): University {

        return when (uniName) {
            "Just" -> University.Just
            "JU" -> University.JU
            "Psut" -> University.Psut
            else -> University.Psut
        }
    }
}
