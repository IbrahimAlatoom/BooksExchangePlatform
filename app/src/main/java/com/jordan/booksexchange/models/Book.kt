package com.jordan.booksexchange.models

enum class University(){
     Just , JU , Psut
}

class Book(val name : String, val publisherId :String? ,val bookId :String, val university : University? ,
             val topic : BookTopic? , val description : String, val ImageUrl : String){
    constructor():this("","","",null,null,"","")
}
