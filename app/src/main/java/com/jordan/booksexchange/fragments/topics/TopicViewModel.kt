package com.jordan.booksexchange.fragments.topics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jordan.booksexchange.models.BookTopic

class TopicViewModel: ViewModel() {
    private val _selectedCardCounter =  MutableLiveData<Int>(0)
    val selectedCardCounter: LiveData<Int>
            get() = _selectedCardCounter

    private val _selectedTopicsList =  MutableLiveData<MutableList<BookTopic>>()
    val selectedTopicsList: LiveData<MutableList<BookTopic>>
        get() = _selectedTopicsList
    init {
        //_selectedCardCounter.value = 1
        _selectedTopicsList.value = mutableListOf()
    }

    fun onCardSelected(topic: BookTopic) {
        if (_selectedTopicsList.value?.contains(topic)!!)
        {
            _selectedCardCounter.value = _selectedCardCounter.value?.minus(1)
            _selectedTopicsList.value?.remove(topic)
            _selectedTopicsList.value = _selectedTopicsList.value
        }
        else if ( ! _selectedTopicsList.value?.contains(topic)!!){
            _selectedCardCounter.value = _selectedCardCounter.value?.plus(1)

            _selectedTopicsList.value?.add(topic)
            // This to trigger the observer
            // Observer gets triggered when the value of the live data changed
            // In this example add value to the list dose not mean change the value of the live data
            // But change the list with new list will change the value of the live data
            _selectedTopicsList.value = _selectedTopicsList.value
        }
        Log.i("counter", _selectedCardCounter.value.toString())
        Log.i("counter", _selectedTopicsList.value.toString())
    }
}