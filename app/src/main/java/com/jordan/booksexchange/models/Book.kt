package com.jordan.booksexchange.models
enum class School(){
    It , Engineering , Medicine
}
enum class University(){
     Just , JU , Psut
}

class Book(val name : String, val publisherId :String , val university : University? ,
           val school :School? , val topic : BookTopic?  ){
    constructor():this("","",null,null,null)
}