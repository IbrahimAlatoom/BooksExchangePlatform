package com.jordan.booksexchange.models

 fun getTopicBookName(type: BookTopic): String {
     return when (type) {
         BookTopic.Medicine -> "Medicine"
         BookTopic.IT -> "IT"
         BookTopic.Science -> "Science"
         BookTopic.Engineering -> "Engineering"
         BookTopic.Pharmacy -> "Pharmacy"
         BookTopic.Dentistry -> "Dentistry"
         BookTopic.Art -> "Art"
         else -> "Another"
     }
 }

fun StringToBookTopic(schName : String) : BookTopic{
    return when(schName) {
        "Medicine" -> BookTopic.Medicine
        "IT" -> BookTopic.IT
        "Science"  -> BookTopic.Science
        "Engineering" ->  BookTopic.Engineering
        "Pharmacy"  ->  BookTopic.Pharmacy
        "Dentistry"  -> BookTopic.Dentistry
        "Art"  ->  BookTopic.Art
        "Another" ->  BookTopic.Another
        else -> BookTopic.Another
    }
}

enum class BookTopic() {
    IT , Medicine , Science , Engineering , Pharmacy , Dentistry , Art , Another
}


data class User(val Id : String, val name: String , val email :String , val phoneNumber: String ,
                val topics:List<BookTopic> , val requests:MutableList<Request> = mutableListOf()) {
    constructor():this("","","","", mutableListOf<BookTopic>()
        , mutableListOf())
}

