package com.jordan.booksexchange.models

 fun getTopicBookName(type: BookTopic): String {
     return when (type) {
         BookTopic.Medicine -> "Medicine"
         BookTopic.ComputerScience -> "Computer Science"
         BookTopic.ComputerEngineering -> "Computer Engineering"
         BookTopic.IndustrialEngineering -> "Industrial Engineering"
         BookTopic.Math -> "Math"
         BookTopic.MechanicalEngineering -> "Mechanical Engineering"
         BookTopic.NetworkEngineering -> "Network Engineering"
         BookTopic.SoftwareEngineering -> "Software Engineering"
     }

 }

fun StringToBookTopic(schName : String) : BookTopic{
    return when(schName) {
        "Medicine" -> BookTopic.Medicine
        "Computer Science" -> BookTopic.ComputerScience
        "Computer Engineering"  -> BookTopic.ComputerEngineering
        "Industrial Engineering" ->  BookTopic.IndustrialEngineering
        "Math"  ->  BookTopic.Math
        "Mechanical Engineering"  -> BookTopic.MechanicalEngineering
        "Network Engineering"  ->  BookTopic.NetworkEngineering
        "Software Engineering" ->  BookTopic.SoftwareEngineering

        else -> BookTopic.Medicine
    }
}

enum class BookTopic() {
    ComputerScience, ComputerEngineering, Medicine , Math , IndustrialEngineering ,
    MechanicalEngineering ,NetworkEngineering , SoftwareEngineering
}


data class User(val Id : String, val name: String , val email :String , val phoneNumber: String ,
                val topics:List<BookTopic>) {
    constructor():this("","","","", mutableListOf<BookTopic>())
}

