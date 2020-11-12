package com.jordan.booksexchange.fragments.topics

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.jordan.booksexchange.R
import com.jordan.booksexchange.items.TopicItem
import com.jordan.booksexchange.models.BookTopic
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_topic.*
import kotlinx.android.synthetic.main.topic_item.*


class TopicFragment : Fragment() {
    private var selectedCardCounter = 0
    private val topicAdapter = GroupAdapter<GroupieViewHolder>()


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

        topicsAdapter()

    }
        // Topic RecycleView
        private fun topicsAdapter(){
            val newName = mutableListOf("")
            // Separator between words in enum class
            handelStringInEnum(newName)
            for (type in newName){
                topicAdapter.add(TopicItem(type,::onTopicItemSelected))
            }
            topics_rv.adapter = topicAdapter
            topics_rv.layoutManager = GridLayoutManager(
                requireContext(), 3, GridLayoutManager.VERTICAL, false)

        }
        // Function to Separate between words in enum class
        private fun handelStringInEnum(newName: MutableList<String>):MutableList<String> {
            var name : String
            for (book in BookTopic.values()) {
                name = book.toString()
                name = name.replace("_", " ")
                newName.add(name)
            }
            newName.remove("")
            return newName
        }
       private fun onTopicItemSelected(){
           selectedCardCounter += 1

       }

    }
