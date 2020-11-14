package com.jordan.booksexchange.items
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.cardview.widget.CardView
import com.jordan.booksexchange.R
import com.jordan.booksexchange.fragments.topics.TopicViewModel
import com.jordan.booksexchange.models.BookTopic
import com.jordan.booksexchange.models.getTopicBookName
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.topic_item.view.*
import org.jetbrains.anko.backgroundColor

class TopicItem(val topic :BookTopic ,val topicViewModel: TopicViewModel,
                val action :(BookTopic)->Unit): Item<GroupieViewHolder>() {

    private var initialColor: ColorStateList? = null

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        initialColor = viewHolder.itemView.topic_item_card.cardBackgroundColor

        viewHolder.itemView.card_item_topic_name_text
            .text = getTopicBookName(topic)
        viewHolder.itemView.topic_item_card.setOnClickListener(::handelHighlightCardColor)

    }

    override fun getLayout(): Int {
        return R.layout.topic_item
    }
    private fun handelHighlightCardColor(view : View){
        // Check if the user can select more topics
        if(topicViewModel.selectedCardCounter.value!! < 3) {
            // Increment the counter and add this item to the list
            action.invoke(topic)
            // Change the color
            val viewCard = view as CardView
            if (viewCard.cardBackgroundColor != initialColor)
                viewCard.setCardBackgroundColor(initialColor)
            else
                viewCard.setCardBackgroundColor(Color.GRAY)

        }
    }
}