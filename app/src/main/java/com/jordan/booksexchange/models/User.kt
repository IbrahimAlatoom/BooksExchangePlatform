package com.jordan.booksexchange.models

enum class BookTopic() {
    Computer_Science, Computer_Engineer, Medicine
}


data class User(val Id : String, val name: String , val email :String , val phoneNumber: String ,
                val topics:List<BookTopic>) {
    constructor():this("","","","", mutableListOf<BookTopic>())
}