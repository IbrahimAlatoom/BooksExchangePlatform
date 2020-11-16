package com.jordan.booksexchange.models

enum class University(){
     Just , JU , Psut
}

class Book(val name : String, val publisherId :String , val university : University? ,
             val topic : BookTopic?  ){
    constructor():this("","",null,null)
}