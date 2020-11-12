package com.jordan.booksexchange.fragments.topics


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
            for (book in BookTopic.values()){
                topicAdapter.add(TopicItem(getTopicBookName(book),::onTopicItemSelected))
            }
            topics_rv.adapter = topicAdapter
            topics_rv.layoutManager = GridLayoutManager(
                requireContext(), 3, GridLayoutManager.VERTICAL, false)
        }
        private fun getTopicBookName(type: BookTopic): String {
            return when(type){
            BookTopic.Medicine -> "Medicine"
            BookTopic.ComputerScience -> "Computer Science"
            BookTopic.ComputerEngineering -> "Computer Engineering"
            BookTopic.IndustrialEngineering -> "Industrial Engineering"
            BookTopic.Math -> "Math"
            BookTopic.MechanicalEngineering -> "MechanicalEngineering"
            BookTopic.NetworkEngineering -> "Network Engineering"
            BookTopic.SoftwareEngineering -> "Software Engineering"
            }
         }
       private fun onTopicItemSelected(){
           selectedCardCounter += 1

       }



    }
