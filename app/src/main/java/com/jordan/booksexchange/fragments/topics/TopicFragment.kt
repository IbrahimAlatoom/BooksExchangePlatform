package com.jordan.booksexchange.fragments.topics


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jordan.booksexchange.R
import com.jordan.booksexchange.items.TopicItem
import com.jordan.booksexchange.models.BookTopic
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_topic.*



class TopicFragment : Fragment() {
    private var selectedCardCounter = 0
    private val topicAdapter = GroupAdapter<GroupieViewHolder>()
    private lateinit var topicViewModel: TopicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topic, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create the view model
        topicViewModel = ViewModelProviders.of(this).get(TopicViewModel::class.java)

        // TRack
        topicViewModel.selectedCardCounter.observe(viewLifecycleOwner, {
             selectedCardCounter -> //
            if(selectedCardCounter == 3) {
                // Show the button
                move_to_home_button.visibility = View.VISIBLE
            }
        })

        topicViewModel.selectedTopicsList.observe(viewLifecycleOwner, {
            selectedTopicsList ->
            Log.i("topics", "user new list: $selectedTopicsList")
        })

        move_to_home_button.setOnClickListener {
            updateUserTopics()

        }

        topicsAdapter()

    }
        // Topic RecycleView
        private fun topicsAdapter(){
            for (book in BookTopic.values()){
                topicAdapter.add(TopicItem(book, topicViewModel, ::onTopicItemSelected))
            }
            topics_rv.adapter = topicAdapter
            topics_rv.layoutManager = GridLayoutManager(
                requireContext(), 3, GridLayoutManager.VERTICAL, false)
        }

       private fun onTopicItemSelected(topic: BookTopic){
           // his wii update the counter
           topicViewModel.onCardSelected(topic)
       }

        private fun updateUserTopics() {
            val db = Firebase.firestore
            val userId = Firebase.auth.currentUser?.uid
            db.collection("users").document("$userId").update("topics"
            , topicViewModel.selectedTopicsList.value).addOnSuccessListener {
                // Update Done
            }
        }

    }
