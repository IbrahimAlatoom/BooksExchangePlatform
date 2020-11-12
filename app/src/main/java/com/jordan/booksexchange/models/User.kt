package com.jordan.booksexchange.models

enum class BookTopic() {
    Computer_Science, Computer_Engineering, Medicine , Math , Industrial_Engineering ,
    Mechanical_Engineering ,Network_Engineering , Software_Engineering
}


data class User(val Id : String, val name: String , val email :String , val phoneNumber: String ,
                val topics:List<BookTopic>) {
    constructor():this("","","","", mutableListOf<BookTopic>())
}