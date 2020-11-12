package com.jordan.booksexchange.models

enum class BookTopic() {
    ComputerScience, ComputerEngineering, Medicine , Math , IndustrialEngineering ,
    MechanicalEngineering ,NetworkEngineering , SoftwareEngineering
}


data class User(val Id : String, val name: String , val email :String , val phoneNumber: String ,
                val topics:List<BookTopic>) {
    constructor():this("","","","", mutableListOf<BookTopic>())
}

