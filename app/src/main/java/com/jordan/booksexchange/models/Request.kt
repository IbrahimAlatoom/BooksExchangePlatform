package com.jordan.booksexchange.models

data class Request(val userId :String?, val publisherId :String? , val postId :String? ,
              var bookName :String ="") {
    constructor() : this("","","","")
}